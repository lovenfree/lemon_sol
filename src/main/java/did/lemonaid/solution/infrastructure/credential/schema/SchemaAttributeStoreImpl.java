package did.lemonaid.solution.infrastructure.credential.schema;


import did.lemonaid.solution.domain.credential.schema.SchemaAttribute;
import did.lemonaid.solution.domain.credential.schema.SchemaAttributeStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SchemaAttributeStoreImpl implements SchemaAttributeStore {
  private final SchemaAttributeRepository schemaAttributeRepository;

  @Override
  public void store(SchemaAttribute initSchemaAttr) {
    schemaAttributeRepository.save(initSchemaAttr);
  }
}
