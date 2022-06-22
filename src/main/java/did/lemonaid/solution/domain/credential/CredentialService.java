package did.lemonaid.solution.domain.credential;



import java.util.List;

public interface CredentialService {
  String registerCredential(CredentialCommand.RegisterCredential command, String tenantID);
//  String updateCredentialInfo(String tenantId, CredentialCommand.UpdateCredential command);
//  String activateCredential(String tenantId, CredentialCommand.ActivateCredential command);
//  //  String deleteCredential(String tenantId);
//  List<CredentialInfo> retrieveCredentials();
//
  List<CredentialInfo> retrieveCredentials();
  CredentialInfo.CredentialDetail retrieveCredential(String credentialDefinitionId);
}
