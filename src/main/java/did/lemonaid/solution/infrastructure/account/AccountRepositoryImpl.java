package did.lemonaid.solution.infrastructure.account;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import did.lemonaid.solution.domain.account.Account;
import did.lemonaid.solution.domain.tenant.Tenant;
import did.lemonaid.solution.interfaces.account.AccountDto;
import did.lemonaid.solution.interfaces.tenant.TenantDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


import static did.lemonaid.solution.domain.account.QAccount.account;
import static did.lemonaid.solution.domain.tenant.QTenant.tenant;
import static org.springframework.util.StringUtils.hasLength;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryImpl {
  private final JPAQueryFactory queryFactory;

  public List<Account> retrieveAccounts(AccountDto.AccountSearchCondition condition) {

    return queryFactory.select(account)
      .from(account)
      .where(accountNameContains(condition.getAccountName()),
        accountIdContains(condition.getAccountId()),
        accountTypeEq(condition.getAccountType()),
        accountStatusEq(condition.getAccountStatus()))
      .orderBy(account.registrationDate.desc())
      .fetch();
  }


  public Boolean exist(String accountId){
    Integer fetchOne = queryFactory.selectOne()
      .from(account)
      .where(account.accountId.eq(accountId))
      .fetchFirst();
    return fetchOne!=null;
  }

  private BooleanExpression accountIdContains(String accountId) {
    return !hasLength(accountId) ? null : account.accountId.contains(accountId);
  }

  private BooleanExpression accountNameContains(String accountName) {
    return !hasLength(accountName) ? null : account.accountName.contains(accountName);
  }

  private BooleanExpression accountTypeEq(Account.AccountType accountType) {
    return accountType ==null ? null : account.accountType.eq(accountType);
  }

  private BooleanExpression accountStatusEq(Account.AccountStatus accountStatus) {
    return accountStatus ==null ? null : account.accountStatus.eq(accountStatus);
  }
}
