package did.lemonaid.solution.infrastructure.tenant;

import did.lemonaid.solution.domain.tenant.Tenant;
import did.lemonaid.solution.domain.tenant.TenantStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TenantStoreImpl  implements TenantStore {
  private final TenantRepository tenantRepository;
  @Override
  public Tenant store(Tenant tenant) {
    return tenantRepository.save(tenant);
  }
}
