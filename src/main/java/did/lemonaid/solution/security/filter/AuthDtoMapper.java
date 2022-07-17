package did.lemonaid.solution.security.filter;

import did.lemonaid.solution.domain.security.AuthCommand;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface AuthDtoMapper {
    @Mapping(source = "password", target = "accountPw")
    AuthCommand.LogIn of(AuthDto.LogInRequest request);

}
