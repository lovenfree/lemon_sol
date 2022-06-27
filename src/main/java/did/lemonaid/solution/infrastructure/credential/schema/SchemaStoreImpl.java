package did.lemonaid.solution.infrastructure.credential.schema;

import did.lemonaid.solution.domain.credential.schema.SchemaStore;
import did.lemonaid.solution.domain.credential.schema.Schemas;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class SchemaStoreImpl implements SchemaStore {

  private final SchemaRepository schemaRepository;

  @Override
  public void store(Schemas schema) {
    schemaRepository.save(schema);
  }
}
