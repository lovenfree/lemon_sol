package did.lemonaid.solution.application.credential;

import did.lemonaid.solution.domain.credential.CredentialCommand;
import did.lemonaid.solution.domain.credential.CredentialInfo;
import did.lemonaid.solution.domain.credential.CredentialService;
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



  public String registerCredential(CredentialCommand.RegisterCredential command, String tenantID) {
    return credentialService.registerCredential(command, tenantID);
  }

  public String updateCredential(String credentialId, CredentialCommand.UpdateCredential registerCredential){
    return credentialService.updateCredential(registerCredential, credentialId);
  }


  public CredentialInfo.CredentialDetail retrieveCredential(String credentialDefinitionId){
    return credentialService.retrieveCredential(credentialDefinitionId);
  }

}
