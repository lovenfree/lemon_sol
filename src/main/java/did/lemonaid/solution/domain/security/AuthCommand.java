package did.lemonaid.solution.domain.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


public class AuthCommand {
  @Getter
  @Builder
  @AllArgsConstructor
  public static class LogIn {
    private final String accountId;
    private final String accountPw;

  }

}
