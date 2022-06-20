package did.lemonaid.solution.infrastructure.credential.schema;

import did.lemonaid.solution.domain.credential.schema.Schemas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchemaRepository extends JpaRepository<Schemas, Long> {
}
