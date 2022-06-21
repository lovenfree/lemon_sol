package did.lemonaid.solution.domain.tenant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class TenantInfo {
  private final String tenantId;
  private final Tenant.TenantType tenantType;
  private final String  tenantName;
  private final String tenantDID;
  private final String tenantInvitationUrl;
  private final Tenant.TenantStatus tenantStatus;
  private final String tenantHomeUrl;
  private final String tenantAddress;
  private final String tenantLogo;
  private final boolean trustTenant;

}
