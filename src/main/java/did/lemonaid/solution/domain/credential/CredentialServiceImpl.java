package did.lemonaid.solution.domain.credential;

import did.lemonaid.solution.domain.credential.schema.SchemaReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
  public String registerCredential(CredentialCommand.RegisterCredential command) {



    return null;
  }

  @Override
  public List<CredentialInfo> retrieveCredentials() {
    return null;
  }

  @Override
  @Transactional
  public CredentialInfo.CredentialDetail retrieveCredential(String credentialDefinitionId) {
    var credential = credentialReader.getCredentialBy(credentialDefinitionId);

//    var credential = credentialReader.getCredentialBy(credentialDefinitionId);

    return     credentialInfoMapper.of(credential);
  }
}
