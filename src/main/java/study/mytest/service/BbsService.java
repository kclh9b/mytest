package study.mytest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.mytest.dto.comment.CommentDto;
import study.mytest.dto.account.AccountDto;
import study.mytest.dto.bbs.BbsDetailDto;
import study.mytest.dto.bbs.BbsDto;
import study.mytest.entity.Account;
import study.mytest.entity.Bbs;
import study.mytest.entity.BbsType;
import study.mytest.entity.Comment;
import study.mytest.repository.AccountRepository;
import study.mytest.repository.BbsRepository;
import study.mytest.repository.BbsTypeRepository;
import study.mytest.repository.CommentRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BbsService {

    private  final AccountRepository accountRepository;
    private final BbsTypeRepository bbsTypeRepository;
    private final BbsRepository bbsRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public long save(String bbsTypeName, AccountDto accountDto, BbsDto bbsDto) {

        BbsType bbsType = bbsTypeRepository.findByName(bbsTypeName)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시판입니다."));

        Account account = accountRepository.findById(accountDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        Bbs bbs = Bbs.createBbs(account, bbsType, bbsDto);
        bbsRepository.save(bbs);

        return bbs.getId();
    }

    @Transactional
    public long update(Long id, BbsDto bbsDto) {
        Bbs bbs = bbsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
        bbs.updateBbs(bbsDto);
        return bbs.getId();
    }

    public BbsDto findOne(Long id) {
        BbsDto bbsDto = bbsRepository.findById(id)
                .map(b -> new BbsDto(b.getId(), b.getTitle(), b.getContent(), b.getCreatedBy(), b.getLastModifiedDate()))
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        return bbsDto;
    }

    @Transactional
    public long createComment(Long bbsId, CommentDto commentDto) {
        Bbs bbs = bbsRepository.findById(bbsId).get();
        Comment comment = Comment.createComment(bbs, commentDto);
        commentRepository.save(comment);
        return comment.getId();
    }

    @Transactional
    public long createNestedComment(Long commentId, CommentDto commentDto) {
        Comment parentComment = commentRepository.findById(commentId).get();
        Comment comment = Comment.createNestedComment(parentComment, commentDto);
        commentRepository.save(comment);
        return comment.getId();
    }

    public BbsDto findOneDetail(Long bbsId) {
        Bbs bbs = bbsRepository.findOneDetail(bbsId);
        return new BbsDto(bbs);
    }

    public BbsDetailDto findOneDetail2(Long bbsId) {
        BbsDetailDto bbsDetailDto = bbsRepository.findOneDetail2(bbsId);
        return bbsDetailDto;
    }
}
