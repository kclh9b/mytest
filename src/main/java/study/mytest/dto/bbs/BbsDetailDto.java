package study.mytest.dto.bbs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.mytest.dto.comment.CommentDto;
import study.mytest.entity.Bbs;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BbsDetailDto {

    private Long bbsId;
    private String accountUserId;
    private String name;
    private String bbsTypeName;
    private String title;
    private String content;
    private List<Bbs> child;
    private List<CommentDto> commentChild;
    private String createdBy;
    private String lastModifiedBy;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

}
