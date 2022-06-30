package did.lemonaid.solution.domain.credential;

import java.util.Optional;

public interface CredentialReader {
  Credential getCredentialBy(String credentialDefinitionID);


  CredentialInfo.SchemaInfo getSchema(Credential credential);

  Credential getCredential(String credentialId);

  Optional<Credential> validByCredentialID(String credentialDefinitionID);
}
