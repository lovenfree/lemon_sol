package did.lemonaid.solution.domain.tenant;

import java.util.List;


public interface TenantService {
  String registerTenant(TenantCommand.RegisterTenant command);
  String updateTenantInfo(String tenantId, TenantCommand.UpdateTenant command);
  String activateTenant(String tenantId, TenantCommand.ActivateTenant command);
//  String deleteTenant(String tenantId);
  List<TenantInfo.TenantDetail> retrieveTenants();

  TenantInfo.TenantDetail retrieveTenant(String tenantId);
  List<TenantInfo.CredentialDetail> retrieveTenantCredentials(String tenantId);
}
