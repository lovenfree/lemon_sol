package did.lemonaid.solution.domain.credential;

import did.lemonaid.solution.domain.BaseEntity;
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


  @OneToOne()
  @JoinColumn(name = "SCHEMA_ID")
  private Schema schema;

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
  @Column(name = "REVOCATION_REGISTRY_SIZE", nullable = false)
  private int revocationRegistrySize;
  @Column(name = "DESCRIPTION")
  private String description;
  @Column(name = "USER_AUTH_PAGE_URL", nullable = false)
  private String userAuthPageUrl;
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
  @Column(name = "TEMP_ITEM_MAPPING", nullable = false)
  private String tempItemMapping;

  @Getter
  @AllArgsConstructor
  public enum CredentialType {
    ID("신분증"), EMPLOYEE_ID("사원증"), DRIVER_LICENSE("운전면허증");

    private final String description;
  }


  @Builder
  public Credential(String credentialId, String credentialName, String credentialDefinitionId, boolean trustCredentialYN, CredentialType credentialType, int revocationRegistrySize, String description, String userAuthPageUrl, boolean expiryDateYN, int validityDays, byte[] backgroundImg, byte[] logoImg, String tempItemMapping) {
    this.credentialId = credentialId;
    this.credentialName = credentialName;
    this.credentialDefinitionId = credentialDefinitionId;
    this.trustCredentialYN = trustCredentialYN;
    this.credentialType = credentialType;
    this.revocationRegistrySize = revocationRegistrySize;
    this.description = description;
    this.userAuthPageUrl = userAuthPageUrl;
    this.expiryDateYN = expiryDateYN;
    this.validityDays = validityDays;
    this.backgroundImg = backgroundImg;
    this.logoImg = logoImg;
    this.tempItemMapping = tempItemMapping;
  }
}
