package study.mytest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.mytest.entity.Bbs;

public interface BbsRepository extends JpaRepository<Bbs, Long> {
}
