package study.mytest.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import study.mytest.entity.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    //@EntityGraph(attributePaths = {"roleAccount"})
    @Query(value = "select a from Account a join fetch a.roleAccount ra join fetch ra.role")
    Optional<Account> findByAccountUserId(String accountUserId);
}
