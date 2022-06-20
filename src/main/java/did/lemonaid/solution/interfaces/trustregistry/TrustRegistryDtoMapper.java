package did.lemonaid.solution.interfaces.trustregistry;

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
    @Mapping(source = "TenantInfo", target = "TrustRegistryDto.TenantInfo")
  })
  List<TrustRegistryDto.TenantInfo> of(List<TenantInfo> tenantInfos);

//  void of(TrustRegistryDto.RegisterCredentialRequest request);
}
