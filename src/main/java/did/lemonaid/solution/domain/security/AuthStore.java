package did.lemonaid.solution.domain.security;

import did.lemonaid.solution.domain.account.Account;

public interface AuthStore {
    Account store(Account initAccount);
    Account initStore(Account initAccount);

}
