package did.lemonaid.solution.infrastructure.credential;

import did.lemonaid.solution.common.exception.EntityNotFoundException;
import did.lemonaid.solution.domain.credential.Credential;
import did.lemonaid.solution.domain.credential.CredentialInfo;
import did.lemonaid.solution.domain.credential.CredentialReader;
import did.lemonaid.solution.domain.credential.schema.SchemaAttribute;
import did.lemonaid.solution.domain.credential.schema.Schemas;
import did.lemonaid.solution.interfaces.trustregistry.TrustRegistryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Slf4j
@RequiredArgsConstructor
public class CredentialReaderImpl implements CredentialReader {
  private final CredentialRepository credentialRepository;

  @Override
  public Credential getCredentialBy(String credentialDefinitionID) {
    return credentialRepository.findByCredentialDefinitionId(credentialDefinitionID)
      .orElseThrow(EntityNotFoundException::new);
  }


  @Override
  public CredentialInfo.SchemaInfo getSchema(Credential credential) {
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
