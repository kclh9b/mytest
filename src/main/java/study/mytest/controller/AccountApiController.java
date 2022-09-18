package study.mytest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import study.mytest.common.ResponseDto;
import study.mytest.config.Constants;
import study.mytest.entity.Account;
import study.mytest.repository.AccountRepository;
import study.mytest.service.account.AccountDto;
import study.mytest.service.account.AccountListDto;
import study.mytest.service.account.AccountSaveDto;
import study.mytest.service.account.AccountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
                .map(a -> new AccountDto(a.getAccountUserId(), a.getName(), a.getAddress().getAddress1()
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
            throw new IllegalStateException("부적절한 접근입니다.");
        }
        session.invalidate();
        ResponseDto responseDto = getSuccessResponseDto("로그아웃 되었습니다.");
        return responseDto;
    }

    @GetMapping("/account/test")
    public ResponseDto test(HttpSession session) {
        AccountDto accountDto = (AccountDto) session.getAttribute("account");
        ResponseDto responseDto = getSuccessResponseDto(accountDto);
        return responseDto;
    }
}