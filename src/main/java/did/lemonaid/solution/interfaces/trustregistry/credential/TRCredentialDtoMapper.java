package did.lemonaid.solution.interfaces.trustregistry.credential;

import did.lemonaid.solution.domain.credential.CredentialCommand;
import did.lemonaid.solution.domain.credential.CredentialInfo;
import org.mapstruct.*;

import java.util.List;


@Mapper(
  componentModel = "spring",
  injectionStrategy = InjectionStrategy.CONSTRUCTOR,
  unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface TRCredentialDtoMapper {

  TRCredentialDto.CredentialDetail of(CredentialInfo.CredentialDetail credential);

  CredentialCommand.RegisterCredential of(TRCredentialDto.RegisterCredentialRequest request);

  CredentialCommand.UpdateCredential of (TRCredentialDto.UpdateCredentialRequest request);


  @Mappings({
    @Mapping(source = "credentials.credentialInfos", target = "credentialInfos")
//    @Mapping(source = "credentials.isLast()", target = "meta.last"),
//    @Mapping(source = "credentials.getTotalElements()", target = "meta.totalElements"),
//    @Mapping(source = "credentials.getTotalPages()", target = "meta.totalPages")
  })
  TRCredentialDto.Credentials of(CredentialInfo.CredentialList credentials);


}
