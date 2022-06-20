package did.lemonaid.solution.application.credential;

import did.lemonaid.solution.domain.credential.CredentialCommand;
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



  public String registerCredential(CredentialCommand.RegisterCredential command) {
    credentialService.registerCredential(command);
    //schema 조회 없으면 생성
    //schema에 대한 업데이트는 별도의 업데이트 api 를 제공 or 있을때 무조건 최신 정보로 업데이트


    return null;
  }
  public CredentialInfo.CredentialDetail retrieveCredential(String credentialDefinitionId){
    return credentialService.retrieveCredential(credentialDefinitionId);
  }

}
