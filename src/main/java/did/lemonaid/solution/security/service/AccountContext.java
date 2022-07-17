package did.lemonaid.solution.security.service;

import did.lemonaid.solution.domain.account.Account;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class AccountContext extends User {
  private final Account account;

  public AccountContext(Account account, Collection<? extends GrantedAuthority> authorities) {
    super(account.getAccountId(), account.getAccountPwHash(), authorities);
    this.account = account;
  }
  public boolean isAccountNonLocked() {
    return account.getAccountPwFailCount() > 5 ? false:true;
  }
  public boolean isEnabled() {
    return account.getAccountStatus().equals(Account.AccountStatus.ACTIVATE)?true:false;
  }

}
