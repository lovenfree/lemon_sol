package did.lemonaid.solution.interfaces.account;

import did.lemonaid.solution.domain.account.AccountCommand;
import did.lemonaid.solution.domain.account.AccountInfo;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface AccountDtoMapper {
    AccountCommand.RegisterAccount of(AccountDto.RegisterAccountRequest request);

    AccountDto.AccountResponse of(String accountId);

    List<AccountDto.AccountInfo> of(List<AccountInfo.AccountDetail> accounts);
    AccountDto.AccountDetail of(AccountInfo.AccountDetail account);
  AccountCommand.UpdateAccount of(AccountDto.UpdateAccountRequest account);
  AccountCommand.UpdateAccountPW of(AccountDto.UpdateAccountPWRequest account);
}
