package did.lemonaid.solution.domain.tenant;


import did.lemonaid.solution.domain.credential.Credential;
import org.mapstruct.*;

import java.util.List;

@Mapper(
  componentModel = "spring",
  injectionStrategy = InjectionStrategy.CONSTRUCTOR,
  unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface TenantInfoMapper {
  @Mappings({
    @Mapping(source = "Tenant", target = "TenantInfo.TenantDetail")
  })
  List<TenantInfo.TenantDetail> of(List<Tenant> tenantInfoList);

//  @Mappings({
//    @Mapping(source = "Tenant", target = "TenantInfo.TenantDetail")
//  })
  TenantInfo.TenantDetail of(Tenant tenant);

  List<TenantInfo.CredentialDetail> credentialOf(List<Credential> credentials);

//  default TenantInfo.TenantDetail of(Tenant tenant) {
//    return TenantInfo.TenantDetail.builder()
//      .tenantAddress(tenant.getTenantAddress())
//      .tenantDID(tenant.getTenantDID())
//      .tenantId(tenant.getTenantId())
//      .tenantHomeUrl(tenant.getTenantHomeUrl())
//      .tenantInvitationUrl(tenant.getTenantInvitationUrl())
//      .tenantLogo(tenant.getTenantLogo() != null ? String.valueOf(tenant.getTenantLogo()) : null)
//      .tenantName(tenant.getTenantName())
//      .tenantStatus(tenant.getTenantStatus())
//      .tenantType(tenant.getTenantType())
//      .trustTenant(tenant.isTrustTenant())
//      .credentialList(tenant.getCredentialList())
//      .build();

//  }

}
