package did.lemonaid.solution.domain.tenant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TenantInfo {
  private final String tenantId;
  private final Tenant.TenantType tenantType;
  private final String  tenantName;
  private final String tenantDID;
  private final String tenantInvitationUrl;
  private final Tenant.TenantStatus tenantStatus;
  private final String tenantLogoPath;

}
