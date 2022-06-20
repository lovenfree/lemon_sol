package did.lemonaid.solution.application.credential;

import did.lemonaid.solution.domain.credential.CredentialInfo;
import did.lemonaid.solution.domain.credential.CredentialService;
import did.lemonaid.solution.domain.tenant.TenantInfo;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@ToString
public class CredentialFacade {
  private final CredentialService credentialService;

  public CredentialInfo retrieveCredential(String credentialDefinitionId){
    return credentialService.retrieveCredential(credentialDefinitionId);
  }

}
