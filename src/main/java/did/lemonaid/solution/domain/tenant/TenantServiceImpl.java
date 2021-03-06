package did.lemonaid.solution.domain.tenant;

import did.lemonaid.solution.interfaces.tenant.TenantDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class TenantServiceImpl implements TenantService{

  private final TenantReader tenantReader;
  private final TenantStore tenantStore;
  private final TenantInfoMapper tenantInfoMapper;

  @Override
  @Transactional
  public String registerTenant(TenantCommand.RegisterTenant command) {
    var initTenant = command.toEntity();
    tenantStore.store(initTenant);
    return initTenant.getTenantId();
  }

  @Override
  @Transactional
  public String updateTenantInfo(String tenantId, TenantCommand.UpdateTenant command) {
    var tenant = tenantReader.getTenant(tenantId);
    tenant.updateTenantInfo(command);
    tenantStore.store(tenant);
    return tenant.getTenantId();
  }

  @Override
  @Transactional
  public String activateTenant(String tenantId, TenantCommand.ActivateTenant command) {
    var tenant = tenantReader.getTenant(tenantId);
    tenant.activateTenant(command);
    tenantStore.store(tenant);
    return tenant.getTenantId();
  }

//  @Override
//  @Transactional
//  public String deleteTenant(String tenantId) {
//    var initTenant = command.toEntity();
//    tenantStore.store(initTenant);
//    return null;
//  }


  @Override
  public List<TenantInfo.TenantDetail> retrieveTenants(TenantDto.TenantSearchCondition condition) {
    var tenants = tenantReader.retrieveTenants(condition);
    return tenantInfoMapper.of(tenants);
  }

  @Override
  public TenantInfo.TenantDetail retrieveTenant(String tenantId) {
    var tenant = tenantReader.getTenant(tenantId);
    return tenantInfoMapper.of(tenant);
  }

  @Override
  public List<TenantInfo.CredentialDetail> retrieveTenantCredentials(String tenantId) {
    var credentials = tenantReader.getTenant(tenantId).getCredentialList();
    return tenantInfoMapper.credentialOf(credentials);
  }

  @Override
  public List<TenantInfo.TenantDetail> retrieveActiveTenants() {
    var tenants = tenantReader.retrieveActiveTenants();
    return tenantInfoMapper.of(tenants);
  }

  @Override
  public List<TenantInfo.TenantDetail> retrieveActiveIssuers() {
    var tenants = tenantReader.retrieveActiveIssuers();
    return tenantInfoMapper.of(tenants);
  }

  @Override
  public List<TenantInfo.TenantDetail> retrieveActiveVerifiers() {
    var tenants = tenantReader.retrieveActiveVerifiers();
    return tenantInfoMapper.of(tenants);
  }
}
