package did.lemonaid.solution.infrastructure.credential;

import did.lemonaid.solution.domain.credential.Credential;
import did.lemonaid.solution.domain.credential.CredentialStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CredentialStoreImpl implements CredentialStore {
  private final CredentialRepository credentialRepository;

  @Override
  public Credential store(Credential toEntity) {
    var credential = credentialRepository.save(toEntity);
    return credential;
  }
}
