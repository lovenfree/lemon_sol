package did.lemonaid.solution.domain.tenant;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


public class TenantCommand {
  @Getter
  @Builder
  @ToString
  public static class RegisterTenantRequest {
    private final String tenantId;
    private final Tenant.TenantType tenantType;
    private final String  tenantName;
    private final String tenantHomeUrl;
    private final String tenantAddress;
    private final String tenantLogoPath;
    private final boolean trustTenant;
    public Tenant toEntity() {
      return Tenant.builder()
        .tenantId(tenantId)
        .tenantType(tenantType)
        .tenantName(tenantName)
        .tenantStatus(Tenant.TenantStatus.PAUSE)
        .tenantHomeUrl(tenantHomeUrl)
        .tenantAddress(tenantAddress)
        .tenantLogoPath(tenantLogoPath)
        .trustTenant(false)
        .build();
    }
  }

  @Getter
  @Builder
  @ToString
  public static class ActivateTenantRequest {
    private final String tenantId;
    private String tenantDID;
    private String tenantInvitationUrl;
  }


  @Getter
  @Builder
  @ToString
  public static class UpdateTenantRequest {
    private final Tenant.TenantType tenantType;
    private final String  tenantName;
    private final String  tenantDID;
    private final String  tenantInvitationUrl;
    private final Tenant.TenantStatus tenantStatus;
    private final String tenantHomeUrl;
    private final String tenantAddress;
    private final String tenantLogoPath;
//    private final boolean trustTenant;
  }
}
