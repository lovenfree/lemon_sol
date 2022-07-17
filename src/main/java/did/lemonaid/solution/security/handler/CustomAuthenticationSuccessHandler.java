package did.lemonaid.solution.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import did.lemonaid.solution.security.filter.AuthDto;
import did.lemonaid.solution.security.service.CustomUserDetailsService;
import did.lemonaid.solution.security.token.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
  private final ObjectMapper objectMapper;
  private final CustomUserDetailsService userService;
  private final JwtTokenProvider jwtTokenProvider;

  public CustomAuthenticationSuccessHandler(final ObjectMapper objectMapper, final CustomUserDetailsService userService, final JwtTokenProvider provider) {
    this.objectMapper = objectMapper;
    this.userService = userService;
    this.jwtTokenProvider = provider;
  }

  @Override
  public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException, ServletException {

    response.setCharacterEncoding("UTF-8");
    response.setStatus(HttpStatus.OK.value());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);

    log.info("^^^^^^^^ IP :"+request.getRemoteAddr());

    //로그인 실패 카운트 초기화
    userService.updateLogInInfo((String)authentication.getPrincipal());
    String jwt = jwtTokenProvider.generateToken(authentication);

    var token = AuthDto.LogInResponse.builder().token(jwt).build();
    objectMapper.writeValue(response.getWriter(), token);
  }
}
