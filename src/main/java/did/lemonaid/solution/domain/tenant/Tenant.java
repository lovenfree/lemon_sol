package did.lemonaid.solution.domain.tenant;

import did.lemonaid.solution.common.exception.ErrorCode;
import did.lemonaid.solution.common.exception.InvalidValueException;
import did.lemonaid.solution.common.util.TokenGenerator;
import did.lemonaid.solution.domain.BaseEntity;
import did.lemonaid.solution.domain.credential.Credential;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;

import javax.persistence.*;
import java.util.List;

import static did.lemonaid.solution.common.exception.ErrorCode.INVALID_ACTIVE_TENANT_PARAM_EXCEPTION;

@Entity
@Getter
@Table(name="TENANT")
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

  @Column(name="TENANT_WALLET_ID")
  private String tenantWalletId;

  @Column(name="TENANT_INVITATION_URL")
  private String tenantInvitationUrl;

  @Column(name="TENANT_STATUS", nullable = false)
  @Enumerated(EnumType.STRING)
  private TenantStatus tenantStatus;

  @Column(name="TENANT_HOME_URL")
  private String tenantHomeUrl;

  @Column(name="TENANT_ADDR")
  private String tenantAddress;

  @Lob
  @Column(name="TENANT_LOGO")
  private String  tenantLogo;

  @Column(name="TENANT_LOGO_FILENAME")
  private String tenantLogoFilename;

  @Column(name="TRUST_TENANT")
  private boolean trustTenant;

  @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL)
  private List<Credential> credentialList = Lists.newArrayList();

  @Getter
  @AllArgsConstructor
  public enum TenantType{
    ISSUER("Issuer"), VERIFIER("Verifier"), BOTH("All");
    private final String description;
  }

  @Getter
  @AllArgsConstructor
  public enum TenantStatus {
    ACTIVATE("활성화"), DEACTIVATE("비활성화 - Activate 전"), PAUSE("정지") ;
    private final String description;
  }

  @Builder
  public Tenant(TenantType tenantType, String tenantName, String tenantHomeUrl, String tenantAddress, String tenantLogo, String tenantLogoFilename, boolean trustTenant) {
    this.tenantId = TokenGenerator.randomCharacterWithPrefix(PREFIX_TENANT);
    this.tenantType = tenantType;
    this.tenantName = tenantName;
    this.tenantStatus = Tenant.TenantStatus.DEACTIVATE;
    this.tenantHomeUrl = tenantHomeUrl;
    this.tenantAddress = tenantAddress;
    this.tenantLogo = tenantLogo;
    this.tenantLogoFilename = tenantLogoFilename;
    this.trustTenant = trustTenant;
  }

  public void activateTenant(TenantCommand.ActivateTenant command){
    if (StringUtils.isBlank(command.getTenantDID())) throw new InvalidValueException("TenantID ",INVALID_ACTIVE_TENANT_PARAM_EXCEPTION);
    if (StringUtils.isBlank(command.getTenantWalletId())) throw new InvalidValueException("Wallet ID",INVALID_ACTIVE_TENANT_PARAM_EXCEPTION);
    if (StringUtils.isBlank(command.getTenantInvitationUrl())) throw new InvalidValueException("InvitationURL", INVALID_ACTIVE_TENANT_PARAM_EXCEPTION);

    this.tenantDID = command.getTenantDID();
    this.tenantWalletId = command.getTenantWalletId();
    this.tenantInvitationUrl = command.getTenantInvitationUrl();

    changeTenantStatus(TenantStatus.ACTIVATE);
    changeTrustTenant();
  }

  public void updateTenantInfo(TenantCommand.UpdateTenant command){
    this.tenantType = command.getTenantType();
    this.tenantName = command.getTenantName();
//    this.tenantDID = command.getTenantDID();
//    this.tenantWalletId = command.getTenantWalletId();
//    this.tenantInvitationUrl = command.getTenantInvitationUrl();
    this.tenantStatus = command.getTenantStatus();
    this.tenantHomeUrl = command.getTenantHomeUrl();
    this.tenantAddress = command.getTenantAddress();
    this.tenantLogo = command.getTenantLogo();
    this.tenantLogoFilename = command.getTenantLogoFilename();
  }



  public void changeTenantStatus(TenantStatus status){
    if (status == this.tenantStatus) throw new InvalidValueException("Tenant Status: "+status.getDescription(), ErrorCode.INVALID_STATUS_PARAM_EXCEPTION);
    this.tenantStatus = status;
  }

  public void changeTrustTenant(){
    if (StringUtils.isBlank(this.tenantDID)) throw new InvalidValueException("TenantDID ",ErrorCode.INVALID_TRUST_TENANT_PARAM_EXCEPTION);
    if (StringUtils.isBlank(this.tenantWalletId)) throw new InvalidValueException("Wallet ID",ErrorCode.INVALID_TRUST_TENANT_PARAM_EXCEPTION);
    if (StringUtils.isBlank(this.tenantInvitationUrl)) throw new InvalidValueException("InvitationURL", ErrorCode.INVALID_TRUST_TENANT_PARAM_EXCEPTION);

    this.trustTenant = true;
  }

  public void changeUnTrustTenant(){
    this.trustTenant = false;
  }




}
