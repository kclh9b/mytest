package study.mytest.service.account;

import lombok.Data;

import java.util.List;

@Data
public class AccountListDto {

    public int size;
    public int page;
    public int accountCnt;
    public List<AccountDto> data;

    public AccountListDto(int size, int page, int accountCnt, List<AccountDto> data) {
        this.size = size;
        this.page = page;
        this.accountCnt = accountCnt;
        this.data = data;
    }
}
