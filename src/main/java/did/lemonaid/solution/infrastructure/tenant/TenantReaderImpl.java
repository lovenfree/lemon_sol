package did.lemonaid.solution.infrastructure.tenant;

import did.lemonaid.solution.domain.tenant.Tenant;
import did.lemonaid.solution.domain.tenant.TenantReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TenantReaderImpl implements TenantReader {
  private final TenantRepository tenantRepository;
  @Override
  public List<Tenant> retrieveTenants() {
    return tenantRepository.findAll();
  }
}
