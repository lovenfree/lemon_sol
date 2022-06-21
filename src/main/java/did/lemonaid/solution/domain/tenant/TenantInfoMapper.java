package did.lemonaid.solution.domain.tenant;

import did.lemonaid.solution.domain.MapStructConfig;
import org.mapstruct.*;

import java.util.List;

@Mapper(
  componentModel = "spring",
  injectionStrategy = InjectionStrategy.CONSTRUCTOR,
  unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface TenantInfoMapper {
  @Mappings({
    @Mapping(source = "Tenant", target = "TenantInfo")
  })
  List<TenantInfo> of(List<Tenant> tenantInfoList);

//  @Mappings({
//    @Mapping(source = "Tenant", target = "TenantInfo")
//  })
//  TenantInfo of(Tenant tenant);

  default TenantInfo of(Tenant tenant) {
    return TenantInfo.builder()
      .tenantAddress(tenant.getTenantAddress())
      .tenantDID(tenant.getTenantDID())
      .tenantId(tenant.getTenantId())
      .tenantHomeUrl(tenant.getTenantHomeUrl())
      .tenantInvitationUrl(tenant.getTenantInvitationUrl())
      .tenantLogo(tenant.getTenantLogo() != null ? String.valueOf(tenant.getTenantLogo()) : null)
      .tenantName(tenant.getTenantName())
      .tenantStatus(tenant.getTenantStatus())
      .tenantType(tenant.getTenantType())
      .trustTenant(tenant.isTrustTenant())
      .build();

  }
  default String toString(Byte[] bytes) {
    return bytes != null ? String.valueOf(bytes): null;
  }
}
