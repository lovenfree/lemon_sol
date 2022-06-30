package did.lemonaid.solution.infrastructure.tenant;

import did.lemonaid.solution.domain.tenant.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TenantRepository extends JpaRepository<Tenant, Long>, TenantRepositoryCustom {
  Optional<Tenant> findByTenantId(String tenantId);
}
