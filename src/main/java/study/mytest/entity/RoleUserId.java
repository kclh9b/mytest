package study.mytest.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@EqualsAndHashCode
public class RoleUserId implements Serializable {

    private Long role;

    private Long account;
}
