package did.lemonaid.solution.infrastructure.tenant;

import did.lemonaid.solution.domain.tenant.Tenant;
import did.lemonaid.solution.interfaces.tenant.TenantDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TenantRepository extends JpaRepository<Tenant, Long> {
  Optional<Tenant> findByTenantId(String tenantId);
  List<Tenant> retrieveTenants(TenantDto.TenantSearchCondition condition);
  List<Tenant> retrieveActiveTenants();
  List<Tenant> retrieveActiveIssuers();
  List<Tenant> retrieveActiveVerifiers();
}
