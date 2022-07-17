package did.lemonaid.solution.security.service;

import did.lemonaid.solution.domain.account.Account;
import did.lemonaid.solution.domain.account.AccountReader;
import did.lemonaid.solution.domain.account.AccountStore;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Component("userDetailsService")
@AllArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
  private final AccountReader accountReader;
  private final AccountStore accountStore;
//  private final AccountMapper mapper;


  @Override
  @Transactional
  public UserDetails loadUserByUsername(final String accountId){
    Account account = accountReader.getAccount(accountId);

    List<GrantedAuthority> roles = new ArrayList<>();
    roles.add(new SimpleGrantedAuthority(String.valueOf(account.getAccountType())));
    var accountContext = new AccountContext(account,roles);
    return accountContext;
  }

//  private UserDetails createUser(String userId, Account account) {
//    if(account.getAccountStatus().equals(Account.AccountStatus.DEACTIVATE)){
//      throw new InvalidAccountException(userId +" 활성화 계정이 아닙니다. ", ErrorCode.INVALID_ACCOUNT_STATUS);
//    }
//
//    List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//    grantedAuthorities.add(new SimpleGrantedAuthority(String.valueOf(account.getAccountType())));
//    return new User(account.getAccountId(), account.getAccountPwHash(), grantedAuthorities);
//
//  }
//
//  public void updateLogInInfo(final String accountId) {
//    Account account = accountReader.getAccount(accountId);
//    account.updateLogInInfo();
//    accountStore.save(account);
//  }
//
//  public void updateLogInFailInfo(final String accountId){
//    Account account = accountReader.getAccount(accountId);
//    account.updateFailInfo();
//    accountStore.save(account);
//  }
}
