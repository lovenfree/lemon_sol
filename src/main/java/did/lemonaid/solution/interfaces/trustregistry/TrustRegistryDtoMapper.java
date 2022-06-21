package did.lemonaid.solution.interfaces.trustregistry;

import did.lemonaid.solution.domain.credential.Credential;
import did.lemonaid.solution.domain.tenant.TenantInfo;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface TrustRegistryDtoMapper {

  @Mappings({
    @Mapping(source = "TenantInfo.TenantDetail", target = "TrustRegistryDto.TenantInfo")
  })
  List<TrustRegistryDto.TenantInfo> of(List<TenantInfo.TenantDetail> tenantInfos);

  List<TrustRegistryDto.CredentialInfo> credentialOf(List<TenantInfo.CredentialDetail> credentials);

//  void of(TrustRegistryDto.RegisterCredentialRequest request);
}
