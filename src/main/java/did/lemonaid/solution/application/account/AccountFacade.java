package did.lemonaid.solution.application.account;

import did.lemonaid.solution.domain.account.AccountCommand;
import did.lemonaid.solution.domain.account.AccountInfo;
import did.lemonaid.solution.domain.account.AccountService;
import did.lemonaid.solution.interfaces.account.AccountDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class AccountFacade {
  private final AccountService accountService;

  public String registerAccount(AccountCommand.RegisterAccount command) {
    return accountService.registerAccount(command);

  }

  public List<AccountInfo.AccountDetail> retrieveAccounts(AccountDto.AccountSearchCondition condition) {
    return accountService.retrieveAccounts(condition);
  }

  public AccountInfo.AccountDetail retrieveAccount(String accountId) {
    return accountService.retrieveAccount(accountId);
  }

  public boolean checkDuplicateAccount(String accountId) {
    return accountService.exist(accountId);
  }

  public void deleteAccount(String accountId) {
    accountService.deleteAccount(accountId);
    return;
  }

  public String updateAccount(String accountId, AccountCommand.UpdateAccount command) {
    return accountService.updateAccount(accountId, command);

  }

  public String changeAccountPw(String accountId, AccountCommand.UpdateAccountPW command) {
    return accountService.changeAccountPw(accountId, command);
  }
}
