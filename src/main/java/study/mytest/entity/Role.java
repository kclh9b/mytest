package study.mytest.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import study.mytest.entity.baseentity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@SequenceGenerator(
        name = "role_seq_gen",
        initialValue = 1,
        allocationSize = 50
)
@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Role extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq_gen")
    @Column(name = "role_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    public Role(RoleType roleType) {
        this.roleType = roleType;
    }

    public static Role initCreateRole(RoleType roleType) {
        Role role = new Role(roleType);
        role.createdBy = "admin";
        role.lastModifiedBy = "admin";
        return role;
    }
}
