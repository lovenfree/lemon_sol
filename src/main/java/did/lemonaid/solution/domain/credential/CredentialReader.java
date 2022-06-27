package did.lemonaid.solution.domain.credential;

import did.lemonaid.solution.domain.credential.schema.Schemas;

public interface CredentialReader {
  Credential getCredentialBy(String credentialDefinitionID);


  CredentialInfo.SchemaInfo getSchema(Credential credential);

  Credential getCredential(String credentialId);
}
