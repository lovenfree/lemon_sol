package did.lemonaid.solution.interfaces.tenant;

import did.lemonaid.solution.domain.tenant.TenantCommand;
import did.lemonaid.solution.domain.tenant.TenantInfo;
import org.mapstruct.*;

import java.util.List;

@Mapper(
  componentModel = "spring",
  injectionStrategy = InjectionStrategy.CONSTRUCTOR,
  unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface TenantDtoMapper {
  TenantCommand.RegisterTenant of(TenantDto.RegisterTenantRequest request);

  TenantDto.TenantResponse of(String tenantId);

  TenantDto.TenantInfo of(TenantInfo.TenantDetail tenantInfo);

  @Mappings({
    @Mapping(source = "TenantInfo", target = "TenantDto.TenantInfo")
  })
  List<TenantDto.TenantInfo> of(List<TenantInfo.TenantDetail> tenantInfos);

  TenantCommand.UpdateTenant of(TenantDto.UpdateTenantRequest request);

  TenantCommand.ActivateTenant
  of(TenantDto.ActivateTenantRequest request);
}
