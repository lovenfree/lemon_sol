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

//  @Mappings({
    @Mapping(source = "credentials.schema.schemaId", target = "TenantInfo.CredentialDetail.schemaId")
//  })
  List<TenantInfo.CredentialDetail>  credentialOf(List<Credential> credentials);



}
