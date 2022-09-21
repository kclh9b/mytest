package study.mytest.config;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import study.mytest.entity.BbsType;
import study.mytest.entity.Role;
import study.mytest.entity.RoleType;
import study.mytest.repository.BbsTypeRepository;
import study.mytest.repository.RoleRepository;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class Init {

    private final BbsTypeRepository bbsTypeRepository;
    private final RoleRepository roleRepository;

    @PostConstruct
    private void init() {
        Role role1 = new Role(RoleType.BRONZE);
        Role role2 = new Role(RoleType.SILVER);
        Role role3 = new Role(RoleType.GOLD);
        roleRepository.save(role1);
        roleRepository.save(role2);
        roleRepository.save(role3);

//        BbsType free = new BbsType("free");
//        bbsTypeRepository.save(free);
//        System.out.println("free = " + free);
    }
}
