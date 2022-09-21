package study.mytest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.mytest.dto.account.AccountDto;
import study.mytest.dto.bbs.BbsDto;
import study.mytest.entity.Account;
import study.mytest.entity.Bbs;
import study.mytest.entity.BbsType;
import study.mytest.repository.AccountRepository;
import study.mytest.repository.BbsRepository;
import study.mytest.repository.BbsTypeRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BbsService {

    private  final AccountRepository accountRepository;
    private final BbsTypeRepository bbsTypeRepository;
    private final BbsRepository bbsRepository;

    public long save(String bbsTypeName, AccountDto accountDto, BbsDto bbsDto) {

        BbsType bbsType = bbsTypeRepository.findByName(bbsTypeName)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시판입니다."));

        Account account = accountRepository.findById(accountDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        Bbs bbs = Bbs.createBbs(account, bbsType, bbsDto);
        bbsRepository.save(bbs);

        return bbs.getId();
    }

    public BbsDto findOne(Long id) {
        BbsDto bbsDto = bbsRepository.findById(id)
                .map(b -> new BbsDto(b.getId(), b.getTitle(), b.getContent(), b.getCreatedBy(), b.getLastModifiedDate()))
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        return bbsDto;
    }
}
