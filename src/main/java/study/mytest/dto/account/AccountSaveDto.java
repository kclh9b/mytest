package study.mytest.dto.account;

import lombok.Data;

@Data
public class AccountSaveDto {
    public String accountUserId;
    public String name;
    public String password;
    public String address1;
    public String address2;
    public String zipcode;

    public AccountSaveDto() {
    }

    public AccountSaveDto(String accountUserId, String name, String password, String address1, String address2, String zipcode) {
        this.accountUserId = accountUserId;
        this.name = name;
        this.password = password;
        this.address1 = address1;
        this.address2 = address2;
        this.zipcode = zipcode;
    }
}
