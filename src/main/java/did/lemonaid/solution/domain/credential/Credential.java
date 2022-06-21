package did.lemonaid.solution.domain.credential;

import did.lemonaid.solution.domain.BaseEntity;
import did.lemonaid.solution.domain.credential.schema.Schemas;
import did.lemonaid.solution.domain.tenant.Tenant;
import lombok.*;
import org.hibernate.annotations.Type;

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
  @Type(type = "org.hibernate.type.BinaryType")
  private byte[] backgroundImg;
  @Lob
  @Column(name = "LOGO_IMG")
  @Type(type = "org.hibernate.type.BinaryType")
  private byte[] logoImg;
  @Column(name = "TEMPLATE_ITEM_MAPPING", nullable = false)
  private String templateItemMapping;

  @Getter
  @AllArgsConstructor
  public enum CredentialType {
    ID("신분증"), EMPLOYEE_ID("사원증"), DRIVER_LICENSE("운전면허증");

    private final String description;
  }


  @Builder
  public Credential(String credentialId, Schemas schema , Tenant tenant, String credentialName, String credentialDefinitionId, boolean trustCredentialYN, CredentialType credentialType, String description, String authLinkUrl, boolean expiryDateYN, int validityDays, byte[] backgroundImg, byte[] logoImg, String templateItemMapping) {
    this.credentialId = credentialId;
    this.credentialName = credentialName;
    this.credentialDefinitionId = credentialDefinitionId;
    this.trustCredentialYN = trustCredentialYN;
    this.credentialType = credentialType;
    this.description = description;
    this.authLinkUrl = authLinkUrl;
    this.expiryDateYN = expiryDateYN;
    this.validityDays = validityDays;
    this.backgroundImg = backgroundImg;
    this.logoImg = logoImg;
    this.templateItemMapping = templateItemMapping;
    this.schema =schema;
    this.tenant =tenant;
  }

  //연관관계 설정
  public void setSchema(Schemas schema){
    this.schema = schema;
  }

  public void setTenant(Tenant tenant){
    this.tenant = tenant;
  }
}
