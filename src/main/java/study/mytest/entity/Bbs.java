package study.mytest.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.mytest.entity.baseentity.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name = "bbs_seq_gen",
        initialValue = 1,
        allocationSize = 50
)
public class Bbs extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bbs_seq_gen")
    @Column(name = "bbs_id")
    private Long id;

    private String title;

    private String content;

    private int layer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bbs_type_id")
    private BbsType bbsType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Bbs parent;

    @OneToMany(mappedBy = "parent")
    private List<Bbs> child = new ArrayList<>();

}
