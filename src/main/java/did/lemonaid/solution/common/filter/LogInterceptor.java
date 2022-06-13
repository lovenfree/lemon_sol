package did.lemonaid.solution.common.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class LogInterceptor extends HandlerInterceptorAdapter {

    @Value("${server.ssl.enabled:false}")
    private boolean sslEnabled;

    /**
     * <pre>
     * rest controller의 허용여부 및  입력정보로를 로깅하는 method
     *
     * rest controller의 실행 전에 수행됨
     * </pre>
     *
     * @param request http servlet의 요청정보  HttpServletRequest 객체
     * @param response http servlet의 응답정보  HttpServletResponse 객체
     * @param handler 해당 함수의 처리에 사용될 handler Object 객체
     * @return Controller를 수행할지 여부 boolean
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        log.info("*** Request [{}] {} Host: {} Server: {}", request.getMethod(), request.getRequestURL(), request.getRemoteHost(), request.getServerName());
        log.debug("protocol: " + request.getProtocol());
        log.info("query: " + request.getQueryString());

        if (sslEnabled == true && request.isSecure() == false &&
                "/eventcheck".contentEquals(request.getRequestURI()) == false &&
                "/error".contentEquals(request.getRequestURI()) == false) {
            response.sendError(HttpStatus.FORBIDDEN.value(), request.getRequestURI() + " is only allowed in ssl");
            log.error(request.getRequestURI() + " is only allowed in ssl");
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * rest controller 출력정보로를 로깅하는 method
     *
     * rest controller의 실행 후에 수행됨
     * </pre>
     *
     * @param request http servlet의 요청정보  HttpServletRequest 객체
     * @param response http servlet의 응답정보  HttpServletResponse 객체
     * @param handler 해당 함수의 처리에 사용될 handler Object 객체
     * @param modelAndView 해당 함수의 처리에 사용될 모델 또는 뷰를 위한 ModelAndView 객체
     */
    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView)
            throws Exception {

        log.info("*** Response [{}] ", response.getStatus());
//        log.debug("Headers: " + response.getHeaderNames());
//        log.debug("Content type: " + response.getContentType());
//        log.debug("Encoding: " + response.getCharacterEncoding());
//        log.debug("Number of bytes: " + response.getHeader("Content-Length"));
    }

    /**
     * <pre>
     * rest controller 성공 여부를 로깅하는 method
     *
     * rest controller의 실행 후에 수행됨
     * </pre>
     *
     * @param request http servlet의 요청정보  HttpServletRequest 객체
     * @param response http servlet의 응답정보  HttpServletResponse 객체
     * @param handler 해당 함수의 처리에 사용될 handler Object 객체
     * @param ex controller 처리가 끝난 후 반환된  Exception 객체
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {

        if (!(ex == null)) {
            log.debug("unable to handle request", ex);
        }
    }
}
