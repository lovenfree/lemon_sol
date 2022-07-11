package did.lemonaid.solution.domain.tenant;

import did.lemonaid.solution.domain.credential.Credential;
import lombok.*;

import java.time.LocalDateTime;


public class TenantInfo {

  @Getter
  @Builder
  @ToString
  public static class TenantDetail {
    private final String tenantId;
    private final Tenant.TenantType tenantType;
    private final String tenantName;
    private final String tenantDID;
    private final String tenantWalletId;
    private final String tenantInvitationUrl;
    private final Tenant.TenantStatus tenantStatus;
    private final String tenantHomeUrl;
    private final String tenantAddress;
    private final String tenantLogo;
    private final String tenantLogoFilename;
    private final boolean trustTenant;
    private final LocalDateTime revisedDate;

  }

  @Getter
//  @Builder
  @ToString
  public static class CredentialDetail {
    private final String credentialId;
    private final String credentialName;
    private final String description;
    private final Credential.CredentialType credentialType;
    private final String credentialDefinitionId;
    private final String schemaId;
    private final String authLinkUrl;
    private final String backgroundImg;
    private final String backgroundImgFilename;
    private final String logoImg;
    private final String logoImgFilename;

    public CredentialDetail(Credential credential) {
      this.credentialId = credential.getCredentialId();
      this.credentialName = credential.getCredentialName();
      this.description = credential.getDescription();
      this.credentialType = credential.getCredentialType();
      this.credentialDefinitionId = credential.getCredentialDefinitionId();
      this.schemaId = credential.getSchema().getSchemaId();
      this.authLinkUrl = credential.getAuthLinkUrl();
      this.backgroundImg = credential.getBackgroundImg();
      this.backgroundImgFilename = credential.getBackgroundImgFilename();
      this.logoImg = credential.getLogoImg();
      this.logoImgFilename = credential.getLogoImgFilename();
    }
  }




}
