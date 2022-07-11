package did.lemonaid.solution.domain.account;


import did.lemonaid.solution.interfaces.account.AccountDto;

import java.util.List;

public interface AccountReader {
    List<Account> retrieveAccounts(AccountDto.AccountSearchCondition condition);

  Account getAccount(String accountId);

  boolean exist(String accountId);
}
