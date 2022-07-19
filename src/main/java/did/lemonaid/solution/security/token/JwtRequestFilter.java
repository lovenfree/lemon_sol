package did.lemonaid.solution.security.token;

import did.lemonaid.solution.common.exception.ErrorCode;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenProvider tokenProvider;
    public static final String AUTHORIZATION_HEADER = "Authorization";

//    모든 Request를 가로챈다
//    Request의 헤더에 토큰이 있는지 확인한다
//    없다면 AutController를 호출해 토큰을 생성한다

    //token의 인증정보를 SecurityContenxt에 저장
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        String jwt = resolveToken(httpServletRequest);
//        String requestURI = httpServletRequest.getRequestURI();
//
//        if(StringUtils.hasText(jwt) && tokenProvider.validateToken(request, jwt)){
//            Authentication authentication = tokenProvider.getAuthentication(jwt);
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            log.info("save to security context: {}", authentication.getName(), requestURI);
//        }
//      System.out.println(chain);
//        chain.doFilter(request, response);
//    }

    // Request의 헤더에 토큰이 있는지 확인한다
    private String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
//            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String jwt = resolveToken(request);
//        String requestURI = httpServletRequest.getRequestURI();

    try {
      if(StringUtils.hasText(jwt) && tokenProvider.validateTokenThrow(jwt)){
          Authentication authentication = tokenProvider.getAuthentication(jwt);

          SecurityContextHolder.getContext().setAuthentication(authentication);
//            log.info("save to security context: {}", authentication.getName(), requestURI);
      }
       } catch (SecurityException e) {
    //Invalid JWT signature
    log.error("[SecurityException] "+e);
    request.setAttribute("exception", ErrorCode.JWT_INVALID_TOKEN);
//          throw new JwtException("Expired");
  } catch (
  MalformedJwtException e) {
    log.error("[MalformedJwtException] "+e);
    request.setAttribute("exception", ErrorCode.JWT_INVALID_TOKEN);
//          throw new JwtException("Expired");
  } catch (
  ExpiredJwtException e) {
    //Expired JWT token
    log.error("[ExpiredJwtException] "+e);
    request.setAttribute("exception", ErrorCode.JWT_EXPIRED_EXCEPTION);
          throw new JwtException("Expired");
  } catch (
  UnsupportedJwtException e) {
    //Unsupported JWT token
    log.error("[UnsupportedJwtException] "+e);
    request.setAttribute("exception", ErrorCode.JWT_INVALID_TOKEN);
//          throw new JwtException("Expired");
  } catch (IllegalArgumentException e) {
    //JWT token compact of handler are invalid.
    log.error("[IllegalArgumentException] "+e);
    request.setAttribute("exception", ErrorCode.JWT_INVALID_TOKEN);
//          throw new JwtException("Expired");
  }catch (Exception e) {
    //JWT token compact of handler are invalid.
    log.error("[Exception] "+e);
    request.setAttribute("exception", ErrorCode.INTERNAL_SERVER_ERROR);
//          throw new JwtException("Expired");
  }
        chain.doFilter(request, response);
  }
}
