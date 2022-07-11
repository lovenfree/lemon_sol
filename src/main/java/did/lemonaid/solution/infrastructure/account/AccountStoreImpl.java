package did.lemonaid.solution.infrastructure.account;

import did.lemonaid.solution.common.exception.ErrorCode;
import did.lemonaid.solution.common.exception.InvalidValueException;
import did.lemonaid.solution.domain.account.Account;
import did.lemonaid.solution.domain.account.AccountStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountStoreImpl implements AccountStore {
    private final AccountRepository accountRepository;

  @Override
  public Account store(Account account) {
    return accountRepository.save(account);
  }

  @Override
  public Account initStore(Account initAccount) {
    if(accountRepository.exist(initAccount.getAccountId())) throw new InvalidValueException(ErrorCode.INVALID_ACCOUNT_REGISTER_EXCEPTION);
    return accountRepository.save(initAccount);
  }

}
