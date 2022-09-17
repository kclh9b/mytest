package study.mytest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.mytest.Entity.Role;
import study.mytest.Entity.RoleType;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleType(RoleType roleType);
}
