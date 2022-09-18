package study.mytest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.mytest.entity.Role;
import study.mytest.entity.RoleType;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleType(RoleType roleType);
}
