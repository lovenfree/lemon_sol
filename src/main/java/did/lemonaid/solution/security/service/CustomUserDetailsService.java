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
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service("UserDetailsService")
@AllArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
  private final AccountReader accountReader;
  private final AccountStore accountStore;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(final String accountId){
    Account account = accountReader.getAccount(accountId);

    List<GrantedAuthority> roles = new ArrayList<>();
    roles.add(new SimpleGrantedAuthority(String.valueOf(account.getAccountType())));
    var accountContext = new AccountContext(account,roles);
    return accountContext;
  }


  public void updateLogInInfo(final String accountId) {
    Account account = accountReader.getAccount(accountId);
    account.updateLogInInfo();
    accountStore.store(account);
  }

  public void updateLogInFailInfo(final String accountId){
    Account account = accountReader.getAccount(accountId);
    account.updateFailInfo();
    accountStore.store(account);
  }
}
