package did.lemonaid.solution.domain.tenant;

import did.lemonaid.solution.interfaces.tenant.TenantDto;

import java.util.List;


public interface TenantService {
  String registerTenant(TenantCommand.RegisterTenant command);
  String updateTenantInfo(String tenantId, TenantCommand.UpdateTenant command);
  String activateTenant(String tenantId, TenantCommand.ActivateTenant command);
//  String deleteTenant(String tenantId);
  List<TenantInfo.TenantDetail> retrieveTenants(TenantDto.TenantSearchCondition condition);

  TenantInfo.TenantDetail retrieveTenant(String tenantId);
  List<TenantInfo.CredentialDetail> retrieveTenantCredentials(String tenantId);

  List<TenantInfo.TenantDetail> retrieveActiveTenants();
  List<TenantInfo.TenantDetail> retrieveActiveIssuers();
  List<TenantInfo.TenantDetail> retrieveActiveVerifiers();
}
