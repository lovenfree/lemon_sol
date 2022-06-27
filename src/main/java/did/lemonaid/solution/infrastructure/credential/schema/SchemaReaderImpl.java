package did.lemonaid.solution.infrastructure.credential.schema;

import did.lemonaid.solution.domain.credential.schema.SchemaReader;
import did.lemonaid.solution.domain.credential.schema.Schemas;
import org.springframework.stereotype.Component;

@Component
public class SchemaReaderImpl implements SchemaReader {
  @Override
  public Schemas getSchema(String schemaId) {
    return null;
  }
}
