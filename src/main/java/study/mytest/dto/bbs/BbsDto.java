package study.mytest.dto.bbs;

import lombok.Data;
import study.mytest.dto.comment.CommentDto;
import study.mytest.entity.Bbs;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class BbsDto {

    private Long bbsId;
    private String title;
    private String content;
    private List<Bbs> child;
    private List<CommentDto> commentChild;
    private String createdBy;
    private String lastModifiedBy;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public BbsDto() {
    }

    public BbsDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public BbsDto(Long bbsId, String title, String content, String createdBy, LocalDateTime lastModifiedDate) {
        this.bbsId = bbsId;
        this.title = title;
        this.content = content;
        this.createdBy = createdBy;
        this.lastModifiedDate = lastModifiedDate;
    }

    public BbsDto(Bbs bbs) {
        this.bbsId = bbs.getId();
        this.title = bbs.getTitle();
        this.content = bbs.getContent();
        this.child = bbs.getChild();
        this.commentChild = bbs.getComments().stream().map(c -> new CommentDto(c)).collect(Collectors.toList());
    }
}
