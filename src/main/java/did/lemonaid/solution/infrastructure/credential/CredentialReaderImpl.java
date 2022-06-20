package did.lemonaid.solution.infrastructure.credential;

import did.lemonaid.solution.common.exception.EntityNotFoundException;
import did.lemonaid.solution.domain.credential.Credential;
import did.lemonaid.solution.domain.credential.CredentialReader;
import did.lemonaid.solution.domain.credential.schema.Schemas;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CredentialReaderImpl implements CredentialReader {
  private final CredentialRepository credentialRepository;

  @Override
  public Credential getCredentialBy(String credentialDefinitionID) {
    return credentialRepository.findByCredentialDefinitionId(credentialDefinitionID)
      .orElseThrow(EntityNotFoundException::new);
  }

//
//  @Override
//  public Schemas getSchema(Credential credential) {
//    return null;
//  }
}
