package did.lemonaid.solution.domain.account;

import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class AccountCommand {

  @Getter
  @Builder
  @RequiredArgsConstructor
  public static class RegisterAccount {
    private final String accountId;
    private final String accountPw;
    private final String accountName;
    private final String mobileNumber;
    private final String phoneNumber;
    private final Account.AccountType accountType;
    private final String email;
    private final String authIp;

    public Account toEntity() {
      return Account.builder()
        .accountId(accountId)
        .accountPwHash(accountPw)
        .accountName(accountName)
        .mobileNumber(mobileNumber)
        .phoneNumber(phoneNumber)
        .accountType(accountType)
        .email(email)
        .authIp(authIp)
        .build();
    }

  }

  @Getter
  @ToString
  @AllArgsConstructor
  public static class UpdateAccount {
    private final String accountName;
    private final String mobileNumber;
    private final String phoneNumber;
    private final String email;
    private final Account.AccountType accountType;
    private final Account.AccountStatus accountStatus;
    private final String authIp;
  }



  @Getter
  @ToString
  @AllArgsConstructor
  public static class UpdateAccountPW {
    private final String accountPw;
  }
}
