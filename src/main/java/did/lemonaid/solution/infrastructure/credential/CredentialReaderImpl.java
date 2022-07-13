package did.lemonaid.solution.infrastructure.credential;

import did.lemonaid.solution.common.exception.EntityNotFoundException;
import did.lemonaid.solution.common.exception.ErrorCode;
import did.lemonaid.solution.domain.credential.Credential;
import did.lemonaid.solution.domain.credential.CredentialInfo;
import did.lemonaid.solution.domain.credential.CredentialReader;
import did.lemonaid.solution.domain.credential.schema.SchemaAttribute;
import did.lemonaid.solution.interfaces.credential.CredentialDto;
import did.lemonaid.solution.interfaces.trustregistry.credential.TRCredentialDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class CredentialReaderImpl implements CredentialReader {
  private final CredentialRepository credentialRepository;

  @Override
  public Credential getCredentialBy(String credentialDefinitionID) {
    return credentialRepository.findByCredentialDefinitionId(credentialDefinitionID)
      .orElseThrow(() -> new EntityNotFoundException(ErrorCode.CREDENTIAL_NOT_FOUND_EXCEPTION));
  }

  @Override
  public Credential retrieveCredential(String credentialId) {
    return credentialRepository.findByCredentialId(credentialId)
      .orElseThrow(() -> new EntityNotFoundException(ErrorCode.CREDENTIAL_NOT_FOUND_EXCEPTION));
  }

  @Override
  public Optional<Credential> validByCredentialID(String credentialDefinitionID) {
    return credentialRepository.findByCredentialDefinitionId(credentialDefinitionID);
  }

  @Override
  public List<CredentialInfo.CredentialListInfo> retrieveCredentials(CredentialDto.CredentialSearchCondition condition) {
    return credentialRepository.retrieveCredentials(condition);
  }

  @Override
  public List<CredentialInfo.CredentialTRListInfo> retrieveTRCredentials(TRCredentialDto.CredentialSearchCondition condition) {
    return credentialRepository.retrieveTRCredentials(condition);
  }

  @Override
  public CredentialInfo.SchemaInfo retrieveSchema(Credential credential) {
    var schemaDomain = credential.getSchema();
    var schemaAttrs = schemaDomain.getSchemaAttributeList();

    //todo: stream
    ArrayList<CredentialInfo.SchemaAttributeInfo> schemaAttrList = new ArrayList();
    for (SchemaAttribute sa : schemaAttrs){
      var schemaAttrInfo = new CredentialInfo.SchemaAttributeInfo(sa);
      schemaAttrList.add(schemaAttrInfo);
    }
      var schemaInfo = new CredentialInfo.SchemaInfo(schemaDomain,schemaAttrList);

    return schemaInfo;

  }




//
//  @Override
//  public Schemas getSchema(Credential credential) {
//    return null;
//  }
}
