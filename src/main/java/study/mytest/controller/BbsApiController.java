package study.mytest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.mytest.dto.comment.CommentDto;
import study.mytest.dto.account.AccountDto;
import study.mytest.dto.bbs.BbsDto;
import study.mytest.dto.common.ResponseDto;
import study.mytest.service.BbsService;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class BbsApiController extends BaseController{

    private final BbsService bbsService;

    @PostMapping("/bbs/{type}")
    public ResponseDto<Long> createBbs(HttpSession session, @PathVariable String type, @RequestBody BbsDto bbsDto) {
        AccountDto accountDto = (AccountDto) session.getAttribute("account");
        long savedId = bbsService.save(type, accountDto, bbsDto);
        return getSuccessResponseDto(savedId);
    }

    @PostMapping("/bbs/{type}/{bbsId}")
    public ResponseDto<Long> updateBbs(@PathVariable Long bbsId, @RequestBody BbsDto bbsDto) {
        long savedId = bbsService.update(bbsId, bbsDto);
        return getSuccessResponseDto(savedId);
    }

    @PostMapping("/bbs/{type}/{bbsId}/comment")
    public ResponseDto<Long> createBbsComment(@PathVariable Long bbsId, @RequestBody CommentDto commentDto) {
        long savedId = bbsService.createComment(bbsId, commentDto);
        return getSuccessResponseDto(savedId);
    }

    @PostMapping("/bbs/{type}/{bbsId}/comment/{commentId}")
    public ResponseDto<Long> createNestedComment(@PathVariable Long commentId, @RequestBody CommentDto commentDto) {
        long savedId = bbsService.createNestedComment(commentId, commentDto);
        return getSuccessResponseDto(savedId);
    }

    @GetMapping("/bbs/{type}/{bbsId}")
    public ResponseDto<BbsDto> findOneDetail(@PathVariable Long bbsId) {
        BbsDto bbsDto = bbsService.findOneDetail(bbsId);
        //BbsDetailDto bbsDetailDto = bbsService.findOneDetail2(bbsId);
        return getSuccessResponseDto(bbsDto);
    }

}
