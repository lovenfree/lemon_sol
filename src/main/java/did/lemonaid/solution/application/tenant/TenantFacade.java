package did.lemonaid.solution.application.tenant;

import did.lemonaid.solution.domain.notification.NotificationService;
import did.lemonaid.solution.domain.tenant.TenantCommand;
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
  private final NotificationService notificationService;

  public List<TenantInfo> retrieveTenants(){
    return tenantService.retrieveTenants();
  }

  public String registerTenant(TenantCommand.RegisterTenantRequest command){
     var tenantId = tenantService.registerTenant(command);
     return tenantId;
  }

  public String updateTenantInfo(String tenantId,TenantCommand.UpdateTenantRequest command){
    var tenantId = tenantService.registerTenant(tenantId,command);
    return tenantId;
  }



  public String activateTenant(String tenantId, TenantCommand.ActivateTenantRequest command){
    var tenantId = tenantService.activateTenant(tenantId, command);
    notificationService.sendEmail("test","test","test");
    return tenantId;
  }
}
