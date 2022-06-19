package did.lemonaid.solution.application.credential;

import did.lemonaid.solution.domain.credential.CredentialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CredentialFacade {
  private final CredentialService credentialService;
}
