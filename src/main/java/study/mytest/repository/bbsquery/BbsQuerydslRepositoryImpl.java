package study.mytest.repository.bbsquery;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.mytest.dto.bbs.BbsDetailDto;
import study.mytest.entity.*;

import javax.persistence.EntityManager;

import java.util.List;

import static study.mytest.entity.QAccount.*;
import static study.mytest.entity.QBbs.*;
import static study.mytest.entity.QBbsType.*;
import static study.mytest.entity.QComment.*;

@Repository
public class BbsQuerydslRepositoryImpl implements BbsQuerydslRepository{

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public BbsQuerydslRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Bbs findOneDetail(Long id) {
        return queryFactory
                .selectFrom(bbs)
                .join(bbs.account, account).fetchJoin()
                .join(bbs.bbsType, bbsType).fetchJoin()
                .join(bbs.child)
                .where(bbs.id.eq(id))
                .fetchOne();
    }

    @Override
    public BbsDetailDto findOneDetail2(Long id) {
        return queryFactory
                .select(Projections.fields(BbsDetailDto.class,
                        bbs.id.as("bbsId"),
                        account.accountUserId,
                        account.name,
                        bbs.bbsType.name.as("bbsTypeName"),
                        bbs.title,
                        bbs.content,
                        bbs.child,
                        bbs.createdBy,
                        bbs.lastModifiedBy,
                        bbs.createdDate,
                        bbs.lastModifiedDate))
                .from(bbs)
                .join(bbs.account, account)
                .join(bbs.bbsType, bbsType)
                .leftJoin(bbs).on(bbs.parent.id.eq(bbs.id)).fetchJoin()
                .leftJoin(comment).on(comment.bbs.id.eq(bbs.id)).fetchJoin()
                .leftJoin(comment).on(comment.parent.id.eq(comment.id).and(comment.layer.eq(0)))
                .where(bbs.id.eq(id))
                .fetchOne();
    }
}
