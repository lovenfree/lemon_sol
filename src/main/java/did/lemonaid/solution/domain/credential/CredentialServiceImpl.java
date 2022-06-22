package did.lemonaid.solution.domain.credential;

import did.lemonaid.solution.domain.credential.schema.SchemaReader;
import did.lemonaid.solution.domain.tenant.TenantReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CredentialServiceImpl implements CredentialService{
  private final CredentialInfoMapper credentialInfoMapper;
  private final TenantReader tenantReader;
  private final CredentialReader credentialReader;
  private final SchemaReader schemaReader;
//  private final CredentialStore credentialStore;

  @Override
  @Transactional
  public String registerCredential(CredentialCommand.RegisterCredential command, String tenantID) {
    var tenant = tenantReader.getTenant(tenantID);
    //schema 조회 없으면 생성
    var schema = schemaReader.getSchema(command.getSchema().getSchemaId());




    //schema에 대한 업데이트는 별도의 업데이트 api 를 제공 or 있을때 무조건 최신 정보로 업데이트
    return null;
  }

  @Override
  public List<CredentialInfo> retrieveCredentials() {
    return null;
  }

  @Override
  public CredentialInfo.CredentialDetail retrieveCredential(String credentialDefinitionId) {
    var credential = credentialReader.getCredentialBy(credentialDefinitionId);
    var schema = credentialReader.getSchema(credential);

    return new CredentialInfo.CredentialDetail(credential,schema);
  }
}
