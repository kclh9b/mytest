package study.mytest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.mytest.entity.BbsType;

import java.util.Optional;

public interface BbsTypeRepository extends JpaRepository<BbsType, Long> {

    Optional<BbsType> findByName(String name);
}
