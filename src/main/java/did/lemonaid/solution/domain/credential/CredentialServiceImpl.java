package did.lemonaid.solution.domain.credential;

import did.lemonaid.solution.domain.credential.schema.SchemaReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CredentialServiceImpl implements CredentialService{
  private final CredentialInfoMapper credentialInfoMapper;
  private final CredentialReader credentialReader;
  private final SchemaReader schemaReader;
//  private final CredentialStore credentialStore;

  @Override
  public List<CredentialInfo> retrieveCredentials() {
    return null;
  }

  @Override
  public CredentialInfo retrieveCredential(String credentialDefinitionId) {
    var credential = credentialReader.getCredentialBy(credentialDefinitionId);
//    var credential = credentialReader.getCredentialBy(credentialDefinitionId);
    System.out.println(credential.getCredentialName());
    System.out.println(credential.getCredentialType());
    System.out.println(credential.getTenant().getTenantId());
    System.out.println(credential.getSchema());
    System.out.println(credential.getSchema().getSchemaId());
    return null;
  }
}
