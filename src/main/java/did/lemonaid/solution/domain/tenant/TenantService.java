package did.lemonaid.solution.domain.tenant;

import java.util.List;

public interface TenantService {
  String registerTenant(TenantCommand command);

  List<TenantInfo> retrieveTenants();
}
