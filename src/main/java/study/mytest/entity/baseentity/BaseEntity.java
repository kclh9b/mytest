package study.mytest.entity.baseentity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BaseEntity extends BaseTimeEntity{

    @CreatedBy
    protected String createdBy;

    @LastModifiedBy
    protected String lastModifiedBy;

    @Column(columnDefinition = "int2 default 1")
    protected String useYn;

}
