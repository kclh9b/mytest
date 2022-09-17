package study.mytest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.mytest.config.Constants;
import study.mytest.service.account.AccountListDto;
import study.mytest.service.account.AccountSaveDto;
import study.mytest.service.account.AccountService;

@RestController
@RequiredArgsConstructor
public class AccountApiController {

    private final AccountService accountService;

    @GetMapping("/account")
    public AccountListDto getMembers(@PageableDefault(size = Constants.ACCOUNT_SIZE) Pageable pageable) {
        AccountListDto accountListDto = accountService.findAll(pageable);
        return accountListDto;
    }

    @PostMapping("/account")
    public long saveAccount(@RequestBody AccountSaveDto saveDto) {
        long savedId = accountService.save(saveDto);
        return savedId;
    }

}