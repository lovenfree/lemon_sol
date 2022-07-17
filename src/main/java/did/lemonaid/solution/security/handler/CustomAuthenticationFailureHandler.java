package did.lemonaid.solution.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;

import did.lemonaid.solution.common.exception.ErrorCode;
import did.lemonaid.solution.common.exception.ErrorResponse;
import org.apache.http.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
  private final ObjectMapper objectMapper;

  public CustomAuthenticationFailureHandler(final ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Override
  public void onAuthenticationFailure(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException exception) throws IOException, ServletException {
    response.setCharacterEncoding("UTF-8");
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);

    String errMsg = "Email or password is invalid.";

    if (exception instanceof BadCredentialsException) {
      errMsg = exception.getMessage();

      //5회 이상 에러시 처리
    }
    ErrorResponse error = ErrorResponse.of(ErrorCode.AUTH_FAIL, errMsg);
    objectMapper.writeValue(response.getWriter(), error);
  }
}

