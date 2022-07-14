package did.lemonaid.solution.application.tenant;

import did.lemonaid.solution.domain.notification.NotificationService;
import did.lemonaid.solution.domain.tenant.TenantCommand;
import did.lemonaid.solution.domain.tenant.TenantInfo;
import did.lemonaid.solution.domain.tenant.TenantService;
import did.lemonaid.solution.interfaces.tenant.TenantDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TenantFacade {
  private final TenantService tenantService;
  private final NotificationService notificationService;

  public List<TenantInfo.TenantDetail> retrieveTenants(TenantDto.TenantSearchCondition condition){
    return tenantService.retrieveTenants(condition);
  }

  public List<TenantInfo.TenantDetail> retrieveActiveTenants(){
    return tenantService.retrieveActiveTenants();
  }

  public List<TenantInfo.TenantDetail> retrieveActiveIssuers(){
    return tenantService.retrieveActiveIssuers();
  }

  public List<TenantInfo.TenantDetail> retrieveActiveVerifiers(){
    return tenantService.retrieveActiveVerifiers();
  }

  public TenantInfo.TenantDetail retrieveTenant(String tenantId){
    return tenantService.retrieveTenant(tenantId);
  }

  public List<TenantInfo.CredentialDetail> retrieveCredentialList(String tenantId){
    return tenantService.retrieveTenantCredentials(tenantId);
  }


  public String registerTenant(TenantCommand.RegisterTenant command){
     var tenantId = tenantService.registerTenant(command);
     return tenantId;
  }

  public String updateTenantInfo(String tenantId, TenantCommand.UpdateTenant command){
    var result = tenantService.updateTenantInfo(tenantId,command);
    return result;
  }



  public String activateTenant(String tenantId, TenantCommand.ActivateTenant command){
    var result = tenantService.activateTenant(tenantId, command);
    notificationService.sendEmail("test","test","test");
    return result;
  }

}
