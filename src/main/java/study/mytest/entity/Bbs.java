package study.mytest.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.mytest.dto.bbs.BbsDto;
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
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bbs_type_id")
    private BbsType bbsType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Bbs parent;

    @OneToMany(mappedBy = "parent")
    private List<Bbs> child = new ArrayList<>();

    @Builder
    public Bbs(String title, String content, int layer, Account account, BbsType bbsType, Bbs parent) {
        this.title = title;
        this.content = content;
        this.layer = layer;
        this.account = account;
        this.bbsType = bbsType;
        this.parent = parent;
    }

    public static Bbs createBbs(Account account, BbsType bbsType, BbsDto bbsDto) {
        return new BbsBuilder()
                .account(account)
                .bbsType(bbsType)
                .title(bbsDto.getTitle())
                .content(bbsDto.getContent())
                .layer(0)
                .build();
    }

}
