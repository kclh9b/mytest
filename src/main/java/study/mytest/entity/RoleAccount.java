package study.mytest.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(RoleUserId.class)
public class RoleAccount {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    public RoleAccount(Role role) {
        this.role = role;
    }

    public RoleAccount(Role role, Account account) {
        this.role = role;
        this.account = account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
