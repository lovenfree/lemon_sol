package did.lemonaid.solution.infrastructure.credential;

import did.lemonaid.solution.domain.credential.Credential;
import did.lemonaid.solution.domain.tenant.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CredentialRepository extends JpaRepository<Credential, Long> {
  Optional<Credential> findByCredentialDefinitionId(String credentialDefinitionId);
}
