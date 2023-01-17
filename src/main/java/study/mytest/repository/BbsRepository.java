package study.mytest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import study.mytest.entity.Bbs;
import study.mytest.repository.bbsquery.BbsQuerydslRepository;

public interface BbsRepository extends JpaRepository<Bbs, Long>, BbsQuerydslRepository {

    //@Query("select b from Bbs b join fetch b.bbsType on ")
    //Bbs findByDataJpa();
}
