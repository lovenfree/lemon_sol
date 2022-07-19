package did.lemonaid.solution.security.provider;

import did.lemonaid.solution.common.exception.ErrorCode;
import did.lemonaid.solution.common.util.Util;
import did.lemonaid.solution.domain.account.Account;
import did.lemonaid.solution.security.service.AccountContext;
import did.lemonaid.solution.security.service.CustomUserDetailsService;
import did.lemonaid.solution.security.token.RestAuthenticationToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.web.util.matcher.IpAddressMatcher;
import org.springframework.stereotype.Component;

import java.util.Collection;

@RequiredArgsConstructor
@Component
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final CustomUserDetailsService userDetailsService;
//    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String accountId = (String) authentication.getPrincipal();

        AccountContext accountContext = (AccountContext) userDetailsService.loadUserByUsername(accountId);
        validateAccess(authentication, accountContext);

        return new RestAuthenticationToken(accountId, null , accountContext.getAuthorities());


    }

    private boolean validateAccess(Authentication authentication, AccountContext accountContext){
        String accountId = (String) authentication.getPrincipal();
        String accountPw = (String) authentication.getCredentials();
        String accessIP = (String)authentication.getDetails();

        if( !accountContext.isEnabled()) {
          throw new BadCredentialsException(ErrorCode.INVALID_ACCOUNT_STATUS.getMessage());
        }

        if(!matchPassword(accountPw, accountContext.getPassword())) {
            userDetailsService.updateLogInFailInfo(accountId);
            throw new BadCredentialsException(ErrorCode.INVALID_ACCOUNT_INFO.getMessage());
        }

        //todo : change ip check
      log.info("utils:"+accessIP);
      IpAddressMatcher matcher = new IpAddressMatcher(accountContext.getAccount().getAuthIp());
      if(accountContext.getAccount().getAuthIp()!="0.0.0.0"&&!Util.isLocalIP(accessIP)&&!matcher.matches(accessIP)) {
        userDetailsService.updateLogInFailInfo(accountId);
        throw new BadCredentialsException(ErrorCode.INVALID_ACCOUNT_IP_EXCEPTION.getMessage());
      }

        return false;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return RestAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private boolean matchPassword(String loginPwd, String password) {
      //passwordEncoder.matched(password, password)
        return BCrypt.checkpw(loginPwd, password);
    }

}
