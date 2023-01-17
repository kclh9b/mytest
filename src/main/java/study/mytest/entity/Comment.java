package study.mytest.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import study.mytest.dto.comment.CommentDto;
import study.mytest.entity.baseentity.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name = "comment_seq_gen",
        initialValue = 1,
        allocationSize = 50
)
public class Comment extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_seq_gen")
    @Column(name = "comment_id")
    private Long id;

    private String content;

    @ColumnDefault("0")
    private int layer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bbs_id")
    private Bbs bbs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent")
    private List<Comment> child = new ArrayList<>();

    @Builder
    public Comment(String content, Comment parent, int layer, Bbs bbs) {
        this.content = content;
        this.parent = parent;
        this.layer = layer;
        this.bbs = bbs;
    }

    public static Comment createComment(Bbs bbs, CommentDto commentDto) {
        return Comment.builder()
                .content(commentDto.getContent())
                .bbs(bbs)
                .layer(0)
                .build();
    }

    public static Comment createNestedComment(Comment parentComment, CommentDto commentDto) {
        return Comment.builder()
                .bbs(parentComment.getBbs())
                .parent(parentComment)
                .content(commentDto.getContent())
                .layer(parentComment.getLayer() + 1)
                .build();
    }
}
