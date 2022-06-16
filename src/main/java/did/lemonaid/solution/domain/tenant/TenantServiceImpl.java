package did.lemonaid.solution.domain.tenant;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TenantServiceImpl implements TenantService{

  private final TenantReader tenantReader;
  private final TenantStore tenantStore;
  private final TenantInfoMapper mapper;

  @Override
  public String registerTenant(TenantCommand command) {
    return null;
  }

  @Override
  public List<TenantInfo> retrieveTenants() {
    var tenents = tenantReader.retrieveTenants();
    return mapper.of(tenents);
  }
}
