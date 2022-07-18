package did.lemonaid.solution.domain.security;

import did.lemonaid.solution.domain.account.Account;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
  componentModel = "spring",
  injectionStrategy = InjectionStrategy.CONSTRUCTOR,
  unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface AuthInfoMapper {

    List<AuthInfo.AccountDetail> of(List<Account> accounts);
  AuthInfo.AccountDetail of(Account account);
}
