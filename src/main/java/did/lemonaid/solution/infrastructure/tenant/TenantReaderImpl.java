package did.lemonaid.solution.infrastructure.tenant;

import did.lemonaid.solution.common.exception.EntityNotFoundException;
import did.lemonaid.solution.common.exception.ErrorCode;
import did.lemonaid.solution.domain.tenant.Tenant;
import did.lemonaid.solution.domain.tenant.TenantReader;
import did.lemonaid.solution.interfaces.tenant.TenantDto;
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
  public Tenant getTenant(String tenantId) {

      return tenantRepository.findByTenantId(tenantId)
        .orElseThrow(()->new EntityNotFoundException(ErrorCode.TENANT_NOT_FOUND_EXCEPTION));

  }

  @Override
  public List<Tenant> retrieveTenants(TenantDto.TenantSearchCondition condition) {
    return tenantRepository.retrieveTenants(condition);
  }

  @Override
  public List<Tenant> retrieveActiveTenants() {
    return tenantRepository.retrieveActiveTenants();
  }

  @Override
  public List<Tenant> retrieveActiveIssuers() {
    return tenantRepository.retrieveActiveIssuers();
  }

  @Override
  public List<Tenant> retrieveActiveVerifiers() {
    return tenantRepository.retrieveActiveVerifiers();
  }
}
