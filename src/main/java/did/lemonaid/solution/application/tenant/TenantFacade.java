package did.lemonaid.solution.application.tenant;

import did.lemonaid.solution.domain.tenant.TenantInfo;
import did.lemonaid.solution.domain.tenant.TenantReader;
import did.lemonaid.solution.domain.tenant.TenantService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class TenantFacade {
  private final TenantService tenantService;

  public List<TenantInfo> retrieveTenants(){
    return tenantService.retrieveTenants();
  }
}
