package study.mytest.dto.account;

import lombok.Data;
import study.mytest.entity.Account;

@Data
public class AccountDto {

    private Long id;
    private String accountUserId;
    private String name;
    private String password;
    private String address1;
    private String address2;
    private String zipcode;

    public AccountDto() {
    }

    public AccountDto(Account account) {
        this.id = account.getId();
        this.accountUserId = account.getAccountUserId();
        this.name = account.getName();
        this.address1 = account.getAddress().getAddress1();
        this.address2 = account.getAddress().getAddress2();
        this.zipcode = account.getAddress().getZipcode();
    }
}
