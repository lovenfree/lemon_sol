package did.lemonaid.solution.infrastructure.credential.schema;

import did.lemonaid.solution.domain.credential.Credential;
import did.lemonaid.solution.domain.credential.schema.Schemas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SchemaRepository extends JpaRepository<Schemas, Long> {
  Optional<Schemas> findBySchemaId(String SchemaId);

}
