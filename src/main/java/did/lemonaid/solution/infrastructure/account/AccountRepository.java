package did.lemonaid.solution.infrastructure.account;

import did.lemonaid.solution.domain.account.Account;
import did.lemonaid.solution.interfaces.account.AccountDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountId(String accountId);

    Boolean exist(String accountId);
  List<Account> retrieveAccounts(AccountDto.AccountSearchCondition condition);
}
