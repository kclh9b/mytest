package study.mytest.repository.accountquery;

import study.mytest.dto.account.AccountDto;

public interface AccountQuerydslRepository {

    AccountDto findAccountDto(Long id);
}
