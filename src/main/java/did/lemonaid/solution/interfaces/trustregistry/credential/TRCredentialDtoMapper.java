package did.lemonaid.solution.interfaces.trustregistry.credential;

import did.lemonaid.solution.domain.credential.CredentialCommand;
import did.lemonaid.solution.domain.credential.CredentialInfo;
import did.lemonaid.solution.domain.tenant.TenantInfo;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;


@Mapper(
  componentModel = "spring",
  injectionStrategy = InjectionStrategy.CONSTRUCTOR,
  unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface TRCredentialDtoMapper {

  List<TRCredentialDto.CredentialInfo> credentialOf(List<TenantInfo.CredentialDetail> credentials);

  TRCredentialDto.CredentialDetail of(CredentialInfo.CredentialDetail credential);

  CredentialCommand.RegisterCredential of(TRCredentialDto.RegisterCredentialRequest request);

  CredentialCommand.UpdateCredential of (TRCredentialDto.UpdateCredentialRequest request);
}
