package study.mytest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.mytest.Entity.RoleUser;
import study.mytest.Entity.RoleUserId;

public interface RoleUserRepository extends JpaRepository<RoleUser, RoleUserId> {

}
