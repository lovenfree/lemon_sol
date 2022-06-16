package did.lemonaid.solution.domain.tenant;

import java.util.List;

public interface TenantReader {
  List<Tenant> retrieveTenants();
}
