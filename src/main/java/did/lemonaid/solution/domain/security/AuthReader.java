package did.lemonaid.solution.domain.security;


import did.lemonaid.solution.domain.account.Account;
import did.lemonaid.solution.interfaces.account.AccountDto;

import java.util.List;

public interface AuthReader {
    List<Account> retrieveAccounts(AccountDto.AccountSearchCondition condition);

  Account getAccount(String accountId);

  boolean exist(String accountId);
}
