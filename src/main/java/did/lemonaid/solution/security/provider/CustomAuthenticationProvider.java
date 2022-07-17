package did.lemonaid.solution.security.provider;

import did.lemonaid.solution.common.exception.ErrorCode;
import did.lemonaid.solution.common.exception.InvalidValueException;
import did.lemonaid.solution.security.service.AccountContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final UserDetailsService userDetailsService;
//    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String accountId = (String) authentication.getPrincipal();
        String accountPw = (String) authentication.getCredentials();

        AccountContext accountContext = (AccountContext) userDetailsService.loadUserByUsername(accountId);
        var loginUser = accountContext.getAccount();
        validateAccess(authentication, accountContext);
//        userDetailsService.updateLogInInfo(accountId);
        return new UsernamePasswordAuthenticationToken(accountId, accountPw, authentication.getAuthorities());

    }

    private boolean validateAccess(Authentication authentication, AccountContext accountContext){
        String accountId = (String) authentication.getPrincipal();
        String accountPw = (String) authentication.getCredentials();
        var loginUser = accountContext.getAccount();


        if(!matchPassword(accountPw, accountContext.getPassword())) {
//          userDetailsService.updateLogInFailInfo(accountId);
            throw new InvalidValueException(ErrorCode.INVALID_ACCOUNT_INFO);
        }

        if(!accountContext.isAccountNonLocked() || !accountContext.isEnabled()) {
            throw new InvalidValueException( ErrorCode.INVALID_ACCOUNT_STATUS);
        }

        //ip check

        return false;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private boolean matchPassword(String loginPwd, String password) {
      //passwordEncoder.matched(password, password)
        return BCrypt.checkpw(loginPwd, password);
    }

}
