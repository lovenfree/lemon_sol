package did.lemonaid.solution.domain.tenant;

import did.lemonaid.solution.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Table(name="TENANT")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tenant extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="ID")
  private Long id;

  @Column(name="TENANT_ID", nullable = false)
  private String tenantId;

  @Column(name="TENANT_TYPE", nullable = false)
  @Enumerated(EnumType.STRING)
  private TenantType tenantType;

  @Column(name="TENANT_NAME", nullable = false)
  private String  tenantName;

  @Column(name="TENANT_DID")
  private String tenantDID;

  @Column(name="TENANT_INVITATION_URL")
  private String tenantInvitationUrl;

  @Column(name="TENANT_STATUS", nullable = false)
  @Enumerated(EnumType.STRING)
  private TenantStatus tenantStatus;

  @Column(name="TENANT_HOME_URL")
  private String tenantHomeUrl;

  @Column(name="TENANT_ADDR")
  private String tenantAddress;

  @Column(name="TENANT_LOGO_PATH")
  private String tenantLogoPath;

  @Column(name="TRUST_TENANT")
  private boolean trustTenant;

  @Getter
  @AllArgsConstructor
  public enum TenantType{
    ISSUER("Issuer"), VERIFIER("Verifier"), BOTH("Issuer/Verifier");
    private final String description;
  }

  @Getter
  @AllArgsConstructor
  public enum TenantStatus {
    ACTIVATE("활성화"), DEACTIVATE("비활성화"), PAUSE("정지") ;
    private final String description;
  }

  @Builder
  public Tenant(String tenantId, TenantType tenantType,String tenantName, TenantStatus status) {
    //validation

    this.tenantId = tenantId;
    this.tenantType = tenantType;
    this.tenantName = tenantName;
    this.tenantStatus = status;
  }
}
