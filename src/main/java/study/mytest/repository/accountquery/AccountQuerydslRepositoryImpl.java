package study.mytest.repository.accountquery;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.mytest.dto.account.AccountDto;

import javax.persistence.EntityManager;

import static study.mytest.entity.QAccount.*;

@Repository
public class AccountQuerydslRepositoryImpl implements AccountQuerydslRepository{

    private EntityManager em;
    private JPAQueryFactory queryFactory;

    public AccountQuerydslRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public AccountDto findAccountDto(Long id) {
        return queryFactory
                .select(Projections.fields(AccountDto.class,
                        account.id,
                        account.name,
                        account.address.address1,
                        account.address.address2,
                        account.address.zipcode))
                .from(account)
                .where(account.id.eq(id))
                .fetchOne();
    }
}
