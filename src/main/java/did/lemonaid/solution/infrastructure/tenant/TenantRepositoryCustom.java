package did.lemonaid.solution.infrastructure.tenant;

import did.lemonaid.solution.domain.tenant.Tenant;
import did.lemonaid.solution.interfaces.tenant.TenantDto;

import java.util.List;

public interface TenantRepositoryCustom {
  List<Tenant> retrieveTenants(TenantDto.TenantSearchCondition condition);
}
