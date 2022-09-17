package study.mytest.config;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import study.mytest.Entity.Role;
import study.mytest.Entity.RoleType;
import study.mytest.repository.RoleRepository;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class RoleInit {

    private final RoleRepository roleRepository;

    @PostConstruct
    private void createRole() {
        Role role1 = new Role(RoleType.BRONZE);
        Role role2 = new Role(RoleType.SILVER);
        Role role3 = new Role(RoleType.GOLD);
        roleRepository.save(role1);
        roleRepository.save(role2);
        roleRepository.save(role3);
    }
}
