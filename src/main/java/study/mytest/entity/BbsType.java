package study.mytest.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.mytest.entity.baseentity.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name="bbs_type_seq_gen",
        initialValue = 1,
        allocationSize = 50
)
public class BbsType extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bbs_type_seq_gen")
    @Column(name = "bbs_type_id")
    private Long id;

    private String name;

    private int bbsRow; /* 예약어로 필드명 변경 row -> bbsRolw */

    private int bbsPage; /* 통일성 위해 필드명 변경 page -> bbsPage */

    public BbsType(String name) {
        this.name = name;
    }
}
