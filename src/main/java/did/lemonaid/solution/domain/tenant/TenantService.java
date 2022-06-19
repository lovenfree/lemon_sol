package did.lemonaid.solution.domain.tenant;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


public interface TenantService {
  String registerTenant(TenantCommand.RegisterTenantRequest command);
  String updateTenantInfo(String tenantId,TenantCommand.UpdateTenantRequest command);
  String activateTenant(String tenantId, TenantCommand.ActivateTenantRequest command);
//  String deleteTenant(String tenantId);
  List<TenantInfo> retrieveTenants();
}
