package study.mytest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.mytest.dto.account.AccountDto;
import study.mytest.dto.account.AccountListDto;
import study.mytest.dto.account.AccountSaveDto;
import study.mytest.entity.*;
import study.mytest.repository.AccountRepository;
import study.mytest.repository.RoleRepository;
import study.mytest.repository.RoleAccountRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountService {

    private final AccountRepository accountRepository;
    private final RoleAccountRepository roleUserRepository;
    private final RoleRepository roleRepository;

    public AccountListDto findAll(Pageable pageable) {

        /* 조회 */
        Page<Account> pageList = accountRepository.findAll(pageable);

        /* dto변환 */
        Page<AccountDto> pageAccountDtos = pageList
                .map(a -> new AccountDto(a.getId(), a.getAccountUserId(), a.getName(), a.getAddress().getAddress1()
                        , a.getAddress().getAddress2(), a.getAddress().getZipcode()));

        /* response dto */
        AccountListDto accountListDto =
                new AccountListDto(pageAccountDtos.getSize(), pageAccountDtos.getNumber(),
                        pageAccountDtos.getNumberOfElements(), pageAccountDtos.getContent());
        return accountListDto;
    }

    @Transactional
    public long save(AccountSaveDto saveDto) {

        /* Account persist */
        Account account = Account.createAccount(saveDto);
        accountRepository.save(account);

        /* Role find */
        Role findRole = roleRepository.findByRoleType(RoleType.BRONZE);

        /* RoleUser Persist */
        RoleAccount roleUser = new RoleAccount(findRole, account);
        roleUserRepository.save(roleUser);

        return account.getId();
    }

    public long login(HttpServletRequest request, AccountDto accountDto) {

        Optional<Account> optAccount = accountRepository.findByAccountUserId(accountDto.getAccountUserId());
        Account account = optAccount.orElseThrow(() -> new IllegalArgumentException("잘못된 아이디 입니다."));

        if(!account.getPassword().equals(accountDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 올바르지 않습니다.");
        }

        accountDto = new AccountDto(account.getId(), account.getAccountUserId(), account.getName(), account.getAddress().getAddress1()
                , account.getAddress().getAddress2(), account.getAddress().getZipcode());

        HttpSession session = request.getSession();
        session.setAttribute("account", accountDto);

        return account.getId();
    }

    @Transactional
    public long update(Long accountId, AccountDto accountDto) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException());
        Address address = new Address(accountDto.getAddress1(), accountDto.getAddress2(), accountDto.getZipcode());
        account.changeAddress(address);
        account.changeName(accountDto.getName());
        return account.getId();
    }
}
