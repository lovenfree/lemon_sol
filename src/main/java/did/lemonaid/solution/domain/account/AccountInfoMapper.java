package did.lemonaid.solution.domain.account;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
  componentModel = "spring",
  injectionStrategy = InjectionStrategy.CONSTRUCTOR,
  unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface AccountInfoMapper {

    List<AccountInfo.AccountDetail> of(List<Account> accounts);
  AccountInfo.AccountDetail of(Account account);
}
