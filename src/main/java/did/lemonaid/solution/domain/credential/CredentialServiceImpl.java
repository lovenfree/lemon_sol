package did.lemonaid.solution.domain.credential;

import did.lemonaid.solution.common.exception.ErrorCode;
import did.lemonaid.solution.common.exception.InvalidValueException;
import did.lemonaid.solution.domain.credential.schema.SchemaReader;
import did.lemonaid.solution.domain.tenant.TenantReader;
import did.lemonaid.solution.interfaces.credential.CredentialDto;
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

  private final CredentialReader credentialReader;

  private final CredentialStore credentialStore;
  private final SchemaSeriesFactory schemaSeriesFactory;

  @Override
  @Transactional
  public String registerCredential(CredentialCommand.RegisterCredential command, String tenantID) {
    if(!credentialReader.validByCredentialID(command.getCredentialDefinitionId()).isEmpty()){
      throw new InvalidValueException(ErrorCode.INVALID_CRE_DEF_ID_EXCEPTION);
    }
    var credential = schemaSeriesFactory.store(command, tenantID);
    return credential.getCredentialId();
  }

  @Override
  public List<CredentialInfo.CredentialListInfo> retrieveCredentials(CredentialDto.CredentialSearchCondition condition) {
    return null;
  }

  @Override
  public CredentialInfo.CredentialDetail retrieveCredential(String credentialDefinitionId) {
    var credential = credentialReader.getCredentialBy(credentialDefinitionId);
    var schema = credentialReader.getSchema(credential);

    return new CredentialInfo.CredentialDetail(credential,schema);
  }

  @Override
  @Transactional
  public String updateCredential(CredentialCommand.UpdateCredential updateCredential, String credentialDefinitionId) {
    var credential = credentialReader.getCredentialBy(credentialDefinitionId);
    credential.updateCredential(updateCredential);
    credentialStore.store(credential);
    return credential.getCredentialId();
  }

  @Override
  public CredentialInfo.CredentialAdminDetail retrieveAdminCredential(String credentialId) {
    return null;
  }
}
