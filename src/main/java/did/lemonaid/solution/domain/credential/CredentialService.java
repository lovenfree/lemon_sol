package did.lemonaid.solution.domain.credential;



import did.lemonaid.solution.interfaces.credential.CredentialDto;
import did.lemonaid.solution.interfaces.trustregistry.credential.TRCredentialDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CredentialService {
  String registerCredential(CredentialCommand.RegisterCredential command, String tenantID);
//  String activateCredential(String tenantId, CredentialCommand.ActivateCredential command);


  List<CredentialInfo.CredentialListInfo> retrieveCredentials(CredentialDto.CredentialSearchCondition condition);
  CredentialInfo.CredentialDetail retrieveCredential(String credentialDefinitionId);

  String updateCredential(CredentialCommand.UpdateCredential registerCredential, String credentialId);

  CredentialInfo.CredentialAdminDetail retrieveAdminCredential(String credentialId);

  String changeCredentialStatus(CredentialCommand.UpdateCredentialStatus credentialStatus);

  CredentialInfo.CredentialList retrieveTRCredentials(TRCredentialDto.CredentialSearchCondition condition, Pageable pageable);
}
