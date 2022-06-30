package did.lemonaid.solution.domain.credential;

import did.lemonaid.solution.common.exception.ErrorCode;
import did.lemonaid.solution.common.exception.InvalidValueException;
import did.lemonaid.solution.common.util.TokenGenerator;
import did.lemonaid.solution.domain.BaseEntity;
import did.lemonaid.solution.domain.credential.schema.Schemas;
import did.lemonaid.solution.domain.tenant.Tenant;
import io.netty.util.internal.StringUtil;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Table(name="CREDENTIAL")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Credential extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "TENANT_ID")
  private Tenant tenant;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "SCHEMA_ID")
  private Schemas schema;

  @Column(name = "CREDENTIAL_ID", nullable = false)
  private String credentialId;

  @Column(name = "SERVICE_EXTENSION_INFO")
  private String serviceExtensionInfo;

  @Column(name = "CREDENTIAL_NAME", nullable = false)
  private String credentialName;
  @Column(name = "CREDENTIAL_DEFINITION_ID", nullable = false)
  private String credentialDefinitionId;
  @Column(name = "TRUST_CREDENTIAL_YN", nullable = false)
  private boolean trustCredentialYN;
  @Column(name = "CREDENTIAL_TYPE", nullable = false)
  @Enumerated(EnumType.STRING)
  private CredentialType credentialType;

  @Column(name = "DESCRIPTION")
  private String description;
  @Column(name = "USER_AUTH_PAGE_URL", nullable = false)
  private String authLinkUrl;
  @Column(name = "EXP_DT_SET_YN", nullable = false)
  private boolean expiryDateYN;
  @Column(name = "VALIDITY_DAYS")
  private int validityDays;
  @Lob
  @Column(name = "BACKGROUND_IMG")
  private String backgroundImg;
  @Column(name = "BACKGROUND_IMG_FILENAME")
  private String backgroundImgFilename;
  @Lob
  @Column(name = "LOGO_IMG")
  private String logoImg;
  @Column(name = "LOGO_IMG_FILENAME")
  private String logoImgFilename;
  @Column(name = "TEMPLATE_ITEM_MAPPING", nullable = false)
  private String templateItemMapping;

  @Getter
  @AllArgsConstructor
  public enum CredentialType {
    ID("신분증"), EMPLOYEE_ID("사원증"), DRIVER_LICENSE("운전면허증");

    private final String description;
  }


  @Builder
  public Credential(Schemas schema , Tenant tenant, String credentialName, String credentialDefinitionId, boolean trustCredentialYN, CredentialType credentialType, String description, String authLinkUrl, boolean expiryDateYN, int validityDays,String serviceExtensionInfo, String backgroundImg, String backgroundImgFilename,
                    String logoImg, String logoImgFilename, String templateItemMapping) {

    if (tenant.getTenantStatus().equals(Tenant.TenantStatus.DEACTIVATE)) throw new InvalidValueException(ErrorCode.INVALID_TENANT_STATUS_EXCEPTION);

    this.credentialId = tenant.getTenantDID()+":TR:"+ TokenGenerator.randomCharacter(6);
    this.credentialName = credentialName;
    this.credentialDefinitionId = credentialDefinitionId;
    //기본값 으로 true
    this.trustCredentialYN = true;
    this.credentialType = credentialType;
    this.description = description;
    this.serviceExtensionInfo = serviceExtensionInfo;
    this.authLinkUrl = authLinkUrl;
    this.expiryDateYN = expiryDateYN;
    this.validityDays = validityDays;
    this.backgroundImg = backgroundImg;
    this.backgroundImgFilename = backgroundImgFilename;
    this.logoImg = logoImg;
    this.logoImgFilename = logoImgFilename;
    this.templateItemMapping = templateItemMapping;
    this.schema =schema;
    this.tenant =tenant;
  }

  public void updateCredential(CredentialCommand.UpdateCredential credential) {

    this.credentialName = credential.getCredentialName();
    this.credentialType = credential.getCredentialType();
    this.description = credential.getDescription();
    this.serviceExtensionInfo = credential.getServiceExtensionInfo();
    this.authLinkUrl = credential.getAuthLinkUrl();
    this.expiryDateYN = credential.isExpiryDateYN();
    this.validityDays = credential.getValidityDays();
    this.backgroundImg = credential.getBackgroundImg();
    this.backgroundImgFilename = credential.getBackgroundImgFilename();
    this.logoImg = credential.getLogoImg();
    this.logoImgFilename = credential.getLogoImgFilename();
    this.templateItemMapping = credential.getTemplateItemMapping();
  }
  //연관관계 설정
  public void setSchema(Schemas schema){
    this.schema = schema;
  }

  public void setTenant(Tenant tenant){
    this.tenant = tenant;
  }
}
