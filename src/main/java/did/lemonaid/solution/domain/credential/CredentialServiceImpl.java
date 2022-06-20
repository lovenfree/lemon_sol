package did.lemonaid.solution.domain.credential;

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
//  private final CredentialStore credentialStore;

  @Override
  public List<CredentialInfo> retrieveCredentials() {
    return null;
  }

  @Override
  public CredentialInfo retrieveCredential(String credentialDefinitionId) {
    var credential = credentialReader.getCredentialBy(credentialDefinitionId);
    System.out.println(credential.toString());
    var schema = credentialReader.getSchema(credential);
    return null;
  }
}
