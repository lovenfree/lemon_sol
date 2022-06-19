package did.lemonaid.solution.interfaces.tenant;

import did.lemonaid.solution.domain.tenant.TenantCommand;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
  componentModel = "spring",
  injectionStrategy = InjectionStrategy.CONSTRUCTOR,
  unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface TenantDtoMapper {
  TenantCommand.RegisterTenantRequest of(TenantDto.RegisterTenantRequest request);

  TenantDto.RegisterResponse of(String tenantId);
}
