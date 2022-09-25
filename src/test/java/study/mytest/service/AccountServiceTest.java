package study.mytest.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import study.mytest.config.Constants;
import study.mytest.dto.account.AccountListDto;
import study.mytest.dto.account.AccountFormDto;
import study.mytest.entity.Account;
import study.mytest.repository.AccountRepository;

import javax.persistence.EntityManager;


import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class AccountServiceTest {

    @Autowired private EntityManager em;
    @Autowired private AccountService accountService;
    @Autowired private AccountRepository accountRepository;

    @Test
    public void findAccountAll() throws Exception {
        //given
        saveAccount("user1", "name1", "password1", "address1", "address2", "zipcode");
        saveAccount("user2", "name1", "password1", "address1", "address2", "zipcode");

        em.flush();
        em.clear();

        //when
        PageRequest pageable = PageRequest.of(0, Constants.ACCOUNT_SIZE);
        AccountListDto list = accountService.findAll(pageable);

        //then
        assertThat(list.getAccountCnt()).isEqualTo(2);
    }

    @Test
    public void createAccount() throws Exception {
        //given
        long savedId = saveAccount("user1", "name1", "password1", "address1", "address2", "zipcode");

        em.flush();
        em.clear();

        //when
        Account findAccount = accountRepository.findById(savedId).orElse(new Account());

        //then
        assertThat(findAccount.getId()).isEqualTo(savedId);
    }

    private long saveAccount(String accountUserId, String name, String password, String address1, String address2, String zipcode) {
        AccountFormDto accountSaveDto = new AccountFormDto(accountUserId, name, password, address1, address2, zipcode);
        long savedId = accountService.save(accountSaveDto);
        return savedId;
    }

}