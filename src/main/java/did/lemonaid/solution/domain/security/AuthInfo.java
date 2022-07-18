package did.lemonaid.solution.domain.security;

import did.lemonaid.solution.domain.account.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;


public class AuthInfo {
  @Getter
  @AllArgsConstructor
  @ToString
  public static class AccountListInfo {
    private final String accountId;
    private final String accountName;
    private final Account.AccountType accountType;
    private final String mobileNumber;
    private final String email;
    private final Account.AccountStatus accountStatus;
    private final LocalDateTime lastLoginDateTime;
    private final LocalDateTime revisedDate;
    }


    @Getter
    @AllArgsConstructor
    @ToString
    public static class AccountDetail {
      private final String accountId;
      private final String accountName;
      private final Account.AccountType accountType;
      private final String email;
      private final String mobileNumber;
      private final String phoneNumber;
      private final Account.AccountStatus accountStatus;
      private final String authIp;
      private final LocalDateTime lastLoginDateTime;
      private final LocalDateTime revisedDate;
    }
}
