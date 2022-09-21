package study.mytest.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.mytest.entity.baseentity.BaseEntity;
import study.mytest.dto.account.AccountSaveDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@SequenceGenerator(
        name = "account_seq_gen",
        initialValue = 1 ,
        allocationSize = 50
)
public class Account extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq_gen")
    @Column(name = "account_id")
    private Long id;

    @Column(unique = true)
    private String accountUserId;

    private String password;

    private String name;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoleAccount> roleAccount = new ArrayList<>();

    @Embedded
    private Address address;

    @Builder
    public Account(String accountUserId, String password, String name, List<RoleAccount> roleAccount, Address address) {
        this.accountUserId = accountUserId;
        this.password = password;
        this.name = name;
        this.roleAccount = roleAccount;
        this.address = address;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changeAddress(Address address) {
        this.address = address;
    }

    public static Account createAccount(AccountSaveDto saveDto) {
        Address address = new Address(saveDto.getAddress1(), saveDto.address2, saveDto.zipcode);
        Account account = new AccountBuilder()
                .accountUserId(saveDto.getAccountUserId())
                .password(saveDto.password)
                .name(saveDto.name)
                .address(address)
                .build();

        account.createdBy = saveDto.accountUserId;
        account.lastModifiedBy = saveDto.accountUserId;

        return account;
    }

    public void addRole(RoleAccount roleAccount) {
        this.roleAccount.add(roleAccount);
        roleAccount.setAccount(this);
    }
}
