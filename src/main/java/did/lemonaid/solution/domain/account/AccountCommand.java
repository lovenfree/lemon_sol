package did.lemonaid.solution.domain.account;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AccountCommand {

    String accountId;
    String accountPw;
    String accountName;
    String mobileNumber;
    String email;
    String authIp;

    public Account toEntity() {
        return Account.builder()
                .accountId(accountId)
                .accountName(accountName)
                .mobileNumber(mobileNumber)
                .email(email)
                .authIp(authIp)
                .build();
    }
}
