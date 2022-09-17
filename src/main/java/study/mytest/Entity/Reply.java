package study.mytest.Entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.mytest.Entity.baseentity.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name = "reply_seq_gen",
        initialValue = 1,
        allocationSize = 50
)
public class Reply extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reply_seq_gen")
    @Column(name = "reply_id")
    private Long id;

    private String content;

    private int layer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Bbs parent;

    @OneToMany(mappedBy = "parent")
    private List<Reply> child = new ArrayList<>();
}
