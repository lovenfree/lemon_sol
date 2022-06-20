package did.lemonaid.solution.domain.tenant;

import did.lemonaid.solution.common.util.TokenGenerator;
import did.lemonaid.solution.domain.BaseEntity;
import did.lemonaid.solution.domain.credential.Credential;
import lombok.*;
import org.assertj.core.util.Lists;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Table(name="TENANT")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tenant extends BaseEntity {
  private static final String PREFIX_TENANT = "tnt_";

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

  @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL)
  private List<Credential> credentialList = Lists.newArrayList();

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

//  @Builder
//  public Tenant(String tenantId, TenantType tenantType,String tenantName, TenantStatus tenantStatus) {
//    //validation
//
//    this.tenantId = tenantId;
//    this.tenantType = tenantType;
//    this.tenantName = tenantName;
//    this.tenantStatus = tenantStatus;
//  }
//
  @Builder
  public Tenant( TenantType tenantType, String tenantName, String tenantHomeUrl, String tenantAddress, String tenantLogoPath, boolean trustTenant) {
    this.tenantId = TokenGenerator.randomCharacterWithPrefix(PREFIX_TENANT);
    this.tenantType = tenantType;
    this.tenantName = tenantName;

    this.tenantStatus = Tenant.TenantStatus.PAUSE;
    this.tenantHomeUrl = tenantHomeUrl;
    this.tenantAddress = tenantAddress;
    this.tenantLogoPath = tenantLogoPath;
    this.trustTenant = trustTenant;
  }



  public void activateTenant(TenantCommand.ActivateTenant command){
    this.tenantDID = command.getTenantDID();
    this.tenantInvitationUrl = command.getTenantInvitationUrl();
    changeTenantStatus(TenantStatus.ACTIVATE);
    changeTrustTenant();
  }

  public void updateTenantInfo(TenantCommand.UpdateTenant command){
    this.tenantType = command.getTenantType();
    this.tenantName = command.getTenantName();
    this.tenantDID = command.getTenantDID();
    this.tenantInvitationUrl = command.getTenantInvitationUrl();
    this.tenantStatus = command.getTenantStatus();
    this.tenantHomeUrl = command.getTenantHomeUrl();
    this.tenantAddress = command.getTenantAddress();
    this.tenantLogoPath = command.getTenantLogoPath();
  }



  public void changeTenantStatus(TenantStatus status){
    this.tenantStatus = status;
  }

  public void changeTrustTenant(){
    this.trustTenant = true;
  }

  public void changeUnTrustTenant(){
    this.trustTenant = false;
  }




}
