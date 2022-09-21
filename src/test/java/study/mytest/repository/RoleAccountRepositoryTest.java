package study.mytest.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.mytest.entity.Account;
import study.mytest.entity.Role;
import study.mytest.entity.RoleAccount;
import study.mytest.entity.RoleType;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class RoleAccountRepositoryTest {

     @Autowired EntityManager em;
     @Autowired AccountRepository accountRepository;
     @Autowired RoleRepository roleRepository;
     @Autowired RoleAccountRepository roleAccountRepository;

     @Test
     public void caseCadePersistTest() throws Exception {
         //given
         Role roleBronze = roleRepository.findByRoleType(RoleType.BRONZE);
         Role roleSilver = roleRepository.findByRoleType(RoleType.SILVER);
         RoleAccount roleAccountBronze = new RoleAccount(roleBronze);
         RoleAccount roleAccountSilver = new RoleAccount(roleSilver);

         Account account = new Account();
         account.addRole(roleAccountBronze);
         account.addRole(roleAccountSilver);

         em.persist(account);

         em.flush();
         em.clear();

         System.out.println("##############################################");

         //when
         List<RoleAccount> list = roleAccountRepository.findAll();

         //then
         list.stream().filter(r -> r.getAccount().getId() == 1);
         for (RoleAccount roleAccount1 : list) {
             System.out.println("roleAccount1.getRole().getRoleType().toString() = " + roleAccount1.getRole().getRoleType().toString());
         }
         Assertions.assertThat(list.size()).isEqualTo(2);

     }

    @Test
    public void caseCadeRemoveTest() throws Exception {
        //given
        Role roleBronze = roleRepository.findByRoleType(RoleType.BRONZE);
        Role roleSilver = roleRepository.findByRoleType(RoleType.SILVER);
        RoleAccount roleAccountBronze = new RoleAccount(roleBronze);
        RoleAccount roleAccountSilver = new RoleAccount(roleSilver);

        Account account = new Account();
        account.addRole(roleAccountBronze);
        account.addRole(roleAccountSilver);

        em.persist(account);

        em.flush();
        em.clear();

        System.out.println("##############################################");

        //when
        Account account1 = accountRepository.findById(1L).get();
        accountRepository.delete(account1);

        em.flush();
        em.clear();

        System.out.println("##############################################");

        //then
        List<RoleAccount> list = roleAccountRepository.findAll();
        list.stream().filter(r -> r.getAccount().getId() == 1);
        Assertions.assertThat(list.size()).isEqualTo(0);

    }

}