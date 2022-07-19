package did.lemonaid.solution.security.token;

import com.fasterxml.jackson.databind.ObjectMapper;
import did.lemonaid.solution.common.exception.ErrorCode;
import did.lemonaid.solution.common.exception.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        ErrorCode exception = (ErrorCode)request.getAttribute("exception");
      System.out.println("JwtAuthenticationEntryPoint: " + exception);
        if(exception == null){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        sendErrorResponse(response, exception);
//

    }

    private void sendErrorResponse(HttpServletResponse response, ErrorCode e) throws IOException {
      ObjectMapper om = new ObjectMapper();

      if(e == null){
            e = ErrorCode.AUTH_FAIL;
        }
        ErrorResponse errorResponse = ErrorResponse.of(e);
        response.setCharacterEncoding("utf-8");
        response.setStatus(HttpStatus.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(om.writeValueAsString(errorResponse));
    }

}
