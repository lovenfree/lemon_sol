package did.lemonaid.solution.application.credential;

import did.lemonaid.solution.domain.credential.CredentialCommand;
import did.lemonaid.solution.domain.credential.CredentialInfo;
import did.lemonaid.solution.domain.credential.CredentialService;
import did.lemonaid.solution.interfaces.credential.CredentialDto;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
@ToString
public class CredentialFacade {
  private final CredentialService credentialService;



  public String registerCredential(CredentialCommand.RegisterCredential command, String tenantID) {
    return credentialService.registerCredential(command, tenantID);
  }

  public String updateCredential(String credentialDefinitionId, CredentialCommand.UpdateCredential registerCredential){
    return credentialService.updateCredential(registerCredential, credentialDefinitionId);
  }


  public CredentialInfo.CredentialDetail retrieveCredential(String credentialDefinitionId){
    return credentialService.retrieveCredential(credentialDefinitionId);
  }

  public List<CredentialInfo.CredentialInfo> retrieveCredentials(Optional<CredentialDto.CredentialSearchCondition> condition) {
  }

}
