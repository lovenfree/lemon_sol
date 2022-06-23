package did.lemonaid.solution.infrastructure.credential.schema;

import did.lemonaid.solution.domain.credential.schema.SchemaAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchemaAttributeRepository extends JpaRepository<SchemaAttribute, Long> {
}
