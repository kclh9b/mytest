package study.mytest.dto.account;

import lombok.Data;

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

    public AccountDto(Long id, String accountUserId, String name, String address1, String address2, String zipcode) {
        this.id = id;
        this.accountUserId = accountUserId;
        this.name = name;
        this.address1 = address1;
        this.address2 = address2;
        this.zipcode = zipcode;
    }
}
