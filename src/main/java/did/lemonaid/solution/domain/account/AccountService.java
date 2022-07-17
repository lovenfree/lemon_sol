package did.lemonaid.solution.domain.account;

import did.lemonaid.solution.interfaces.account.AccountDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AccountService {
    String registerAccount(AccountCommand.RegisterAccount command);

  List<AccountInfo.AccountDetail> retrieveAccounts(AccountDto.AccountSearchCondition condition);

  AccountInfo.AccountDetail retrieveAccount(String accountId);

  boolean exist(String accountId);

  void deleteAccount(String accountId);

  String updateAccount(String accountId, AccountCommand.UpdateAccount command);

  String changeAccountPw(String accountId, AccountCommand.UpdateAccountPW command);
}
