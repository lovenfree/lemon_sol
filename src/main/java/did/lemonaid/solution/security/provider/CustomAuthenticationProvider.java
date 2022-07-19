package did.lemonaid.solution.security.provider;

import did.lemonaid.solution.common.exception.ErrorCode;
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
import org.springframework.stereotype.Component;

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
      for (GrantedAuthority accounts: accountContext.getAuthorities()) {
        System.out.println("$$$"+accounts.getAuthority());
      }

        return new RestAuthenticationToken(accountId, null , accountContext.getAuthorities());

    }

    private boolean validateAccess(Authentication authentication, AccountContext accountContext){
        String accountId = (String) authentication.getPrincipal();
        String accountPw = (String) authentication.getCredentials();

        if( !accountContext.isEnabled()) {
          throw new BadCredentialsException(ErrorCode.INVALID_ACCOUNT_STATUS.getMessage());
        }

        if(!matchPassword(accountPw, accountContext.getPassword())) {
            userDetailsService.updateLogInFailInfo(accountId);
            throw new BadCredentialsException(ErrorCode.INVALID_ACCOUNT_INFO.getMessage());
        }

        //ip check

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
