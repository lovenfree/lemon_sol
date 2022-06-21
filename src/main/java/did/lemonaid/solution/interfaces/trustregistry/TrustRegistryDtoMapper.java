package did.lemonaid.solution.interfaces.trustregistry;

import did.lemonaid.solution.domain.credential.Credential;
import did.lemonaid.solution.domain.tenant.TenantCommand;
import did.lemonaid.solution.domain.tenant.TenantInfo;
import did.lemonaid.solution.interfaces.tenant.TenantDto;
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

  TenantCommand.ActivateTenant activateOf(TrustRegistryDto.ActivateTenantRequest request);

 TrustRegistryDto.TenantResponse of(String tenantId);

//  void of(TrustRegistryDto.RegisterCredentialRequest request);
}
