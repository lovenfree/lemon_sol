package did.lemonaid.solution.domain.tenant;

import java.util.List;

public interface TenantReader {
  Tenant getTenant(String tenantId);
  List<Tenant> retrieveTenants();
}
