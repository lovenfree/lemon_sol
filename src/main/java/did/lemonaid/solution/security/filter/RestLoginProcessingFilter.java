package did.lemonaid.solution.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import did.lemonaid.solution.security.dto.AuthDto;
import did.lemonaid.solution.security.token.RestAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class RestLoginProcessingFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    private ObjectMapper objectMapper;

    // "/api/v1/login"로 요청이 들어 올 경우 이 필터가 동작합니다.
    public RestLoginProcessingFilter() {
      super(new AntPathRequestMatcher("/v1/auth/login"));
    }

    @Override
    public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
      // 들어온 요청이 위의 URI는 맞지만 넘어온 파라미터가 REST API 기반의 요청이 아닐 경우 예외를 던집니다.

      if (!isRest(request)) {
        throw new IllegalStateException("It's not a REST request.");
      }

      // REST API 기반의 인증 요청이 맞다면 요청 데이터를 모든 인증관련 처리에 사용하려고 만든 SignRequestDto에 바인딩합니다.
      final AuthDto.LogInRequest login = objectMapper.readValue(request.getReader(), AuthDto.LogInRequest.class);

      // 인증되지 않은 사용자의 토큰을 생성해야 하므로 RestAuthenticationToken의 생성자를 호출합니다.
      return getAuthenticationManager().authenticate(new RestAuthenticationToken(login.getAccountId(), login.getPassword()));
    }

    private boolean isRest(final HttpServletRequest request) {
      return "application/json".equals(request.getHeader("Content-Type"));
    }


}
