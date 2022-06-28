package did.lemonaid.solution.domain.credential.schema;

import java.util.Optional;

public interface SchemaReader {
    Optional<Schemas> getSchema(String schemaId);
}
