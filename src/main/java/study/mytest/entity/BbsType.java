package study.mytest.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import study.mytest.entity.baseentity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@DynamicInsert
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

    private Integer bbsRow; /* 예약어로 필드명 변경 row -> bbsRolw */

    private Integer bbsPage; /* 통일성 위해 필드명 변경 page -> bbsPage */

    public BbsType(String name) {
        this.name = name;
    }

    public static BbsType initCreateBbsType(String name, Integer row, Integer page) {
        BbsType bbsType = new BbsType(name);
        bbsType.bbsRow = row;
        bbsType.bbsPage = page;
        bbsType.createdBy = "admin";
        bbsType.lastModifiedBy = "admin";
        return bbsType;
    }
}
