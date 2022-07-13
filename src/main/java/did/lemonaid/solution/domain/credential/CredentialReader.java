package did.lemonaid.solution.domain.credential;

import did.lemonaid.solution.interfaces.credential.CredentialDto;
import did.lemonaid.solution.interfaces.trustregistry.credential.TRCredentialDto;

import java.util.List;
import java.util.Optional;

public interface CredentialReader {
  Credential getCredentialBy(String credentialDefinitionID);


  CredentialInfo.SchemaInfo retrieveSchema(Credential credential);

  Credential retrieveCredential(String credentialId);

  Optional<Credential> validByCredentialID(String credentialDefinitionID);

  List<CredentialInfo.CredentialListInfo> retrieveCredentials(CredentialDto.CredentialSearchCondition condition);


  List<CredentialInfo.CredentialTRListInfo> retrieveTRCredentials(TRCredentialDto.CredentialSearchCondition condition);
}
