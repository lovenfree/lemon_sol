package did.lemonaid.solution.interfaces.account;

import did.lemonaid.solution.domain.account.AccountCommand;
import did.lemonaid.solution.domain.account.AccountInfo;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface AccountDtoMapper {
    AccountCommand of(AccountDto.RegisterRequest request);

    AccountDto.RegisterResponse of(AccountInfo accountInfo);
}
