package did.lemonaid.solution.infrastructure.account;

import did.lemonaid.solution.common.exception.EntityNotFoundException;
import did.lemonaid.solution.common.exception.ErrorCode;
import did.lemonaid.solution.domain.account.Account;
import did.lemonaid.solution.domain.account.AccountReader;
import did.lemonaid.solution.infrastructure.tenant.TenantRepository;
import did.lemonaid.solution.interfaces.account.AccountDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountReaderImpl implements AccountReader {
  private final AccountRepository accountRepository;
  @Override
  public List<Account> retrieveAccounts(AccountDto.AccountSearchCondition condition) {
    return accountRepository.retrieveAccounts(condition);
  }

  @Override
  public Account getAccount(String accountId) {
    return accountRepository.findByAccountId(accountId)
      .orElseThrow(()->new EntityNotFoundException(ErrorCode.ACCOUNT_NOT_FOUND));
  }

  @Override
  public boolean exist(String accountId) {
    return accountRepository.exist(accountId);
  }
}
