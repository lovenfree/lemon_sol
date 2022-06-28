package did.lemonaid.solution.infrastructure.credential.schema;

import did.lemonaid.solution.domain.credential.schema.SchemaReader;
import did.lemonaid.solution.domain.credential.schema.Schemas;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SchemaReaderImpl implements SchemaReader {
  private final SchemaRepository schemaRepository;
  @Override
  public Optional<Schemas> getSchema(String schemaId) {
    return schemaRepository.findBySchemaId(schemaId);

  }
}
