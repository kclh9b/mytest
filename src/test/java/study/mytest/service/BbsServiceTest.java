package study.mytest.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.mytest.dto.account.AccountDto;
import study.mytest.dto.account.AccountFormDto;
import study.mytest.dto.bbs.BbsDto;
import study.mytest.entity.*;
import study.mytest.repository.AccountRepository;
import study.mytest.repository.BbsTypeRepository;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
class BbsServiceTest {

    @Autowired EntityManager em;
    @Autowired AccountRepository accountRepository;
    @Autowired AccountService accountService;
    @Autowired BbsTypeRepository bbsTypeRepository;
    @Autowired BbsService bbsService;

    @Test
    public void createBbs() throws Exception {
        //given
        BbsType bbsType = new BbsType("free");
        bbsTypeRepository.save(bbsType);

        long savedId = saveAccount("user1", "name1", "password1", "address1", "address2", "zipcode");

        em.flush();
        em.clear();

        BbsDto bbsDto = new BbsDto("title", "content");

        AccountDto accountDto = accountRepository.findAccountDto(1L);

        bbsService.save("free", accountDto, bbsDto);

        em.flush();
        em.clear();

        //when
        BbsDto result = bbsService.findOne(1L);

        //then
        Assertions.assertThat(result.getCreatedBy()).isEqualTo(accountDto.getAccountUserId());
    }

    private long saveAccount(String accountUserId, String name, String password, String address1, String address2, String zipcode) {
        AccountFormDto accountSaveDto = new AccountFormDto(accountUserId, name, password, address1, address2, zipcode);
        long savedId = accountService.save(accountSaveDto);
        return savedId;
    }

}