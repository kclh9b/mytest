package study.mytest.dto.bbs;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BbsDto {

    private Long bbsId;
    private String title;
    private String content;
    private String createdBy;
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
}
