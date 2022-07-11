package did.lemonaid.solution.domain.tenant;

import did.lemonaid.solution.interfaces.tenant.TenantDto;

import java.util.List;

public interface TenantReader {
  Tenant getTenant(String tenantId);
  List<Tenant> retrieveTenants(TenantDto.TenantSearchCondition condition);
  List<Tenant> retrieveActiveTenants();
  List<Tenant> retrieveActiveIssuers();
  List<Tenant> retrieveActiveVerifiers();


}
