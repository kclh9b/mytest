package study.mytest.service.account;

import lombok.Data;

@Data
public class AccountSaveDto {
    public String accountUserId;
    public String name;
    public String password;
    public String address1;
    public String address2;
    public String zipcode;
}
