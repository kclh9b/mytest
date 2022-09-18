package study.mytest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.mytest.entity.RoleAccount;
import study.mytest.entity.RoleUserId;

public interface RoleUserRepository extends JpaRepository<RoleAccount, RoleUserId> {

}
