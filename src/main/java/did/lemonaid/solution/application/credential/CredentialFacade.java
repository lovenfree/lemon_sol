package did.lemonaid.solution.application.credential;

import did.lemonaid.solution.domain.credential.CredentialCommand;
import did.lemonaid.solution.domain.credential.CredentialInfo;
import did.lemonaid.solution.domain.credential.CredentialService;
import did.lemonaid.solution.interfaces.credential.CredentialDto;
import did.lemonaid.solution.interfaces.trustregistry.credential.TRCredentialDto;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
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

  public CredentialInfo.CredentialAdminDetail retrieveAdminCredential(String credentialId){
    return credentialService.retrieveAdminCredential(credentialId);
  }
  public List<CredentialInfo.CredentialListInfo> retrieveCredentials(CredentialDto.CredentialSearchCondition condition) {
    return credentialService.retrieveCredentials(condition);
  }

  public String changeCredentialStatus(CredentialCommand.UpdateCredentialStatus credentialStatus) {
    return credentialService.changeCredentialStatus(credentialStatus);
  }

  public CredentialInfo.CredentialList retrieveTRCredentials(TRCredentialDto.CredentialSearchCondition condition, Pageable pageable) {
    return credentialService.retrieveTRCredentials(condition, pageable);
  }
}
