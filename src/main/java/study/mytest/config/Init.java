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
        Role roleBronze = Role.initCreateRole(RoleType.BRONZE);
        Role roleSilver = Role.initCreateRole(RoleType.SILVER);
        Role roleGold = Role.initCreateRole(RoleType.GOLD);
        roleRepository.save(roleBronze);
        roleRepository.save(roleSilver);
        roleRepository.save(roleGold);

        BbsType free = BbsType.initCreateBbsType("free", 10, 10);
        BbsType notice = BbsType.initCreateBbsType("notice", 10, 10);
        bbsTypeRepository.save(free);
        bbsTypeRepository.save(notice);
    }
}
