package study.mytest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.mytest.Entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
