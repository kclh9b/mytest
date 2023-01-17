package study.mytest.repository.bbsquery;

import study.mytest.dto.bbs.BbsDetailDto;
import study.mytest.entity.Bbs;

public interface BbsQuerydslRepository {

    Bbs findOneDetail(Long id);

    BbsDetailDto findOneDetail2(Long id);
}
