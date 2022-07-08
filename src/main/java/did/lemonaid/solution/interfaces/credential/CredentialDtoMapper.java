package did.lemonaid.solution.interfaces.credential;

import did.lemonaid.solution.domain.credential.CredentialInfo;
import org.mapstruct.*;

import java.util.List;

@Mapper(
  componentModel = "spring",
  injectionStrategy = InjectionStrategy.CONSTRUCTOR,
  unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface CredentialDtoMapper {
//  @Mappings({
//    @Mapping(source = "TenantInfo", target = "TenantDto.TenantInfo")
//    @Mapping(source = "TenantInfo", target = "TenantDto.TenantInfo")
//  })
  List<CredentialDto.CredentialInfo> of(List<CredentialInfo.CredentialListInfo> credentials);

  CredentialDto.CredentialAdminDetail credentialOf(CredentialInfo.CredentialAdminDetail credential);
}
