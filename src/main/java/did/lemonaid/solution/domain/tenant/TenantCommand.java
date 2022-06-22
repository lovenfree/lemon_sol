package did.lemonaid.solution.domain.tenant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


public class TenantCommand {
  @Getter
  @Builder
  @AllArgsConstructor
  public static class RegisterTenant {
//    private final String tenantId;
    private final Tenant.TenantType tenantType;
    private final String  tenantName;
    private final String tenantHomeUrl;
    private final String tenantAddress;
    private final String tenantLogo;
    private final String tenantLogoFilename;
//    private final boolean trustTenant;

    public Tenant toEntity() {
      return Tenant.builder()
        .tenantType(tenantType)
        .tenantName(tenantName)
        .tenantHomeUrl(tenantHomeUrl)
        .tenantAddress(tenantAddress)
        .tenantLogo(tenantLogo)
        .tenantLogoFileName(tenantLogoFilename)
        .build();
    }
  }

  @Getter
  @ToString
  @AllArgsConstructor
  public static class ActivateTenant {
    private final String tenantDID;
    private final String tenantInvitationUrl;
  }


  @Getter
  @ToString
  @AllArgsConstructor
  public static class UpdateTenant {
    private final Tenant.TenantType tenantType;
    private final String  tenantName;
    private final String  tenantDID;
    private final String  tenantInvitationUrl;
    private final Tenant.TenantStatus tenantStatus;
    private final String tenantHomeUrl;
    private final String tenantAddress;
    private final String tenantLogo;
    private final String tenantLogoFilename;
//    private final boolean trustTenant;
  }
}
