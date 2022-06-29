package did.lemonaid.solution.interfaces.trustregistry.tenant;

import did.lemonaid.solution.domain.tenant.TenantCommand;
import did.lemonaid.solution.domain.tenant.TenantInfo;
import org.mapstruct.*;

import java.util.List;

@Mapper(
  componentModel = "spring",
  injectionStrategy = InjectionStrategy.CONSTRUCTOR,
  unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface TRTenantDtoMapper {

  @Mappings({
    @Mapping(source = "TenantInfo.TenantDetail", target = "TRTenantDto.TenantInfo")
  })
  List<TRTenantDto.TenantInfo> of(List<TenantInfo.TenantDetail> tenantInfos);

  TenantCommand.ActivateTenant activateOf(TRTenantDto.ActivateTenantRequest request);

  TRTenantDto.TenantResponse of(String tenantId);
}
