package study.mytest.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.mytest.entity.Comment;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private Long commentId;
    private String content;
    private Integer layer;
    private String createBy;
    private String lastModifiedBy;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public CommentDto(Comment comment) {
        this.commentId = comment.getId();
        this.content = comment.getContent();
        this.layer = comment.getLayer();
        this.createBy = comment.getCreatedBy();
        this.lastModifiedBy = comment.getLastModifiedBy();
        this.createdDate = comment.getCreatedDate();
        this.lastModifiedDate = comment.getLastModifiedDate();
    }
}
