package did.lemonaid.solution.domain.security;


import did.lemonaid.solution.security.provider.CustomAuthenticationProvider;
import did.lemonaid.solution.security.token.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

  private final JwtTokenProvider tokenProvider;
  private final AuthenticationManagerBuilder authenticationManagerBuilder;
  private final CustomAuthenticationProvider provider;
  @Override
  public String logIn(AuthCommand.LogIn command) {
    return null;
  }
}
