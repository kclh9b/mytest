package study.mytest.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor
@EqualsAndHashCode
public class RoleUserId implements Serializable {

    private Long role;

    private Long account;
}
