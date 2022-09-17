package study.mytest.service.account;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.mytest.Entity.Account;
import study.mytest.Entity.Role;
import study.mytest.Entity.RoleType;
import study.mytest.Entity.RoleUser;
import study.mytest.repository.AccountRepository;
import study.mytest.repository.RoleRepository;
import study.mytest.repository.RoleUserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountService {

    private final AccountRepository accountRepository;
    private final RoleUserRepository roleUserRepository;
    private final RoleRepository roleRepository;

    public AccountListDto findAll(Pageable pageable) {

        /* 조회 */
        Page<Account> pageList = accountRepository.findAll(pageable);

        /* dto변환 */
        Page<AccountDto> pageAccountDtos = pageList
                .map(a -> new AccountDto(a.getAccountUserId(), a.getName(), a.getAddress().getAddress1()
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
        RoleUser roleUser = new RoleUser(findRole, account);
        roleUserRepository.save(roleUser);

        return account.getId();
    }

}
