package did.lemonaid.solution.infrastructure.account;

import did.lemonaid.solution.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long>, AccountRepositoryCustom {
    Optional<Account> findByAccountId(String accountId);
}
