package did.lemonaid.solution.domain.account;


import did.lemonaid.solution.interfaces.account.AccountDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {
  private final AccountStore accountStore;
  private final AccountReader accountReader;
  private final AccountInfoMapper accountInfoMapper;
  @Override
  @Transactional
  public String registerAccount(AccountCommand.RegisterAccount command){
      //account 의 경우 중복체크를 위해 initStore 사용
      var iniAccount = command.toEntity();
      accountStore.initStore(iniAccount);
      return iniAccount.getAccountId();
  }

  @Override
  public List<AccountInfo.AccountDetail> retrieveAccounts(AccountDto.AccountSearchCondition condition) {
    var accounts = accountReader.retrieveAccounts(condition);
    return accountInfoMapper.of(accounts);

  }

  @Override
  public AccountInfo.AccountDetail retrieveAccount(String accountId) {
    var account = accountReader.getAccount(accountId);
    return accountInfoMapper.of(account);
  }

  @Override
  @Transactional
  public boolean exist(String accountId) {
    return accountReader.exist(accountId);
  }

  @Override
  @Transactional
  public void deleteAccount(String accountId) {
    var account = accountReader.getAccount(accountId);
    account.deleteAccount();
    accountStore.store(account);
    return;
  }


  @Override
  @Transactional
  public String changeAccountPw(String accountId, AccountCommand.UpdateAccountPW command) {
    var account = accountReader.getAccount(accountId);
    account.updatePwInfo(command);
    accountStore.store(account);
    return accountId;
  }

  @Override
  @Transactional
  public String updateAccount(String accountId, AccountCommand.UpdateAccount command) {
    var account = accountReader.getAccount(accountId);
    account.updateAccountInfo(command);
    accountStore.store(account);
    return accountId;
  }

}
