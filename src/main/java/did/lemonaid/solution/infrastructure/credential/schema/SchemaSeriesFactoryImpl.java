package did.lemonaid.solution.infrastructure.credential.schema;

import did.lemonaid.solution.common.exception.ErrorCode;
import did.lemonaid.solution.common.exception.InvalidValueException;
import did.lemonaid.solution.domain.credential.*;
import did.lemonaid.solution.domain.credential.schema.SchemaAttributeStore;
import did.lemonaid.solution.domain.credential.schema.SchemaReader;
import did.lemonaid.solution.domain.credential.schema.SchemaStore;
import did.lemonaid.solution.domain.credential.schema.Schemas;
import did.lemonaid.solution.domain.tenant.TenantReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class SchemaSeriesFactoryImpl implements SchemaSeriesFactory {
  private final SchemaStore schemaStore;
  private final SchemaAttributeStore schemaAttributeStore;
  private final TenantReader tenantReader;
  private final SchemaReader schemaReader;
  private final CredentialStore credentialStore;

  @Override
  public Credential store(CredentialCommand.RegisterCredential command, String tenantId) {
    var tenant = tenantReader.getTenant(tenantId);
    var schema = schemaReader.getSchema(command.getSchema().getSchemaId()).orElse(null);

    var schemaAttrList = command.getSchema().getSchemaAttributeList();

    if (schema == null) {
      //    schema 조회 없으면 생성
      schema = command.getSchema().toEntity();

      schemaStore.store(schema);

      Schemas finalSchema = schema;
      schemaAttrList.stream()
        .map(requestSchemaAttribute -> {
          var initSchemaAttr = requestSchemaAttribute.toEntity(finalSchema);
          schemaAttributeStore.store(initSchemaAttr);

          return null;
        }).collect(Collectors.toList());
    }
    var credential = credentialStore.store(command.toEntity(tenant,schema));

    return credential;
  }
}
