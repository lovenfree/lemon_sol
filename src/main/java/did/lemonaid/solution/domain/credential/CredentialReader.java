package did.lemonaid.solution.domain.credential;

import did.lemonaid.solution.domain.credential.schema.Schemas;

public interface CredentialReader {
  Credential getCredentialBy(String credentialDefinitionID);
  Schemas getSchema(Credential credential);


}
