package study.mytest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import study.mytest.dto.common.ResponseDto;
import study.mytest.config.Constants;
import study.mytest.repository.AccountRepository;
import study.mytest.dto.account.AccountDto;
import study.mytest.dto.account.AccountListDto;
import study.mytest.dto.account.AccountSaveDto;
import study.mytest.service.AccountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AccountApiController extends BaseController{

    private final AccountService accountService;
    private final AccountRepository accountRepository;

    @GetMapping("/account")
    public ResponseDto<AccountListDto> getMembers(@PageableDefault(size = Constants.ACCOUNT_SIZE) Pageable pageable) {
        AccountListDto accountListDto = accountService.findAll(pageable);
        return getSuccessResponseDto(accountListDto);
    }

    @PostMapping("/account")
    public long saveAccount(@RequestBody AccountSaveDto saveDto) {
        long savedId = accountService.save(saveDto);
        return savedId;
    }

    @GetMapping("/account/{accountId}")
    public ResponseDto<AccountDto> accountInfo(@PathVariable Long accountId) {
        return accountRepository.findById(accountId)
                .map(a -> new AccountDto(a.getId(), a.getAccountUserId(), a.getName(), a.getAddress().getAddress1()
                        , a.getAddress().getAddress2(), a.getAddress().getZipcode()))
                .map(a -> getSuccessResponseDto(a))
                .orElse(null);
    }

    @PostMapping("/account/login")
    public ResponseDto<Long> login(@RequestBody AccountDto accountDto, HttpServletRequest request) {
        return getSuccessResponseDto("로그인 되었습니다.", accountService.login(request, accountDto));
    }

    @PostMapping("/account/logout")
    public ResponseDto<String> logout(HttpSession session) {
        if(session == null) {
            throw new IllegalStateException("잘못된 접근입니다.");
        }
        session.invalidate();
        ResponseDto responseDto = getSuccessResponseDto("로그아웃 되었습니다.");
        return responseDto;
    }

    @PutMapping("/account/{accountId}")
    public ResponseDto updateAccount(@PathVariable Long accountId, HttpSession session, @RequestBody AccountDto accountDto) {
        AccountDto sessionDto = Optional.ofNullable(session)
                .map(s -> (AccountDto) s.getAttribute("account"))
                .orElseThrow(() -> new IllegalStateException("잘못된 접근입니다."));
        if(!sessionDto.getId().equals(accountId)) {
            throw new IllegalStateException("접근 권한이 없습니다.");
        }
        accountService.update(accountId, accountDto);
        return getSuccessResponseDto("변경되었습니다.");
    }

    @GetMapping("/account/test")
    public ResponseDto test(HttpSession session) {
        AccountDto accountDto = (AccountDto) session.getAttribute("account");
        ResponseDto responseDto = getSuccessResponseDto(accountDto);
        return responseDto;
    }
}