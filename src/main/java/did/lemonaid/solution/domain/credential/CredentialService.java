package did.lemonaid.solution.domain.credential;



import did.lemonaid.solution.interfaces.credential.CredentialDto;

import java.util.List;

public interface CredentialService {
  String registerCredential(CredentialCommand.RegisterCredential command, String tenantID);
//  String updateCredentialInfo(String tenantId, CredentialCommand.UpdateCredential command);
//  String activateCredential(String tenantId, CredentialCommand.ActivateCredential command);
//  //  String deleteCredential(String tenantId);


  List<CredentialInfo.CredentialListInfo> retrieveCredentials(CredentialDto.CredentialSearchCondition condition);
  CredentialInfo.CredentialDetail retrieveCredential(String credentialDefinitionId);

  String updateCredential(CredentialCommand.UpdateCredential registerCredential, String credentialId);

  CredentialInfo.CredentialAdminDetail retrieveAdminCredential(String credentialId);
}
