package did.lemonaid.solution.domain.tenant;

public interface TenantStore {
  Tenant store(Tenant initTenant);
}
