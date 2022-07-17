package did.lemonaid.solution.application.auth;

import did.lemonaid.solution.domain.security.AuthCommand;
import did.lemonaid.solution.domain.security.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthFacade {
  private final AuthService authService;

  public String logIn(AuthCommand.LogIn command) {
    return authService.logIn(command);

  }

}
