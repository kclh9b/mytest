package study.mytest.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Commit
class AccountTest {

    @Autowired EntityManager em;

    @Test
    public void updateTest() throws Exception {
        //given
        Account account = new Account();
        em.persist(account);

        em.flush();
        em.clear();
        System.out.println("###########################################################");

        //when
        Account account1 = em.find(Account.class, 1L);
        Address address = new Address("111", "222", "333");
        account1.changeAddress(address);
        account1.changeName("sss");

        System.out.println("###########################################################");

    }

}