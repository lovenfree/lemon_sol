package did.lemonaid.solution.domain.tenant;

import did.lemonaid.solution.common.util.Util;
import did.lemonaid.solution.domain.credential.Credential;
import lombok.*;

import java.lang.reflect.Array;
import java.util.List;


public class TenantInfo {


  @Getter
  @ToString
  public static class TenantDetail {
    private final String tenantId;
    private final Tenant.TenantType tenantType;
    private final String tenantName;
    private final String tenantDID;
    private final String tenantInvitationUrl;
    private final Tenant.TenantStatus tenantStatus;
    private final String tenantHomeUrl;
    private final String tenantAddress;
    private final String tenantLogo;
    private final boolean trustTenant;
//    private final List<CredentialDetail> credentialList;

    public TenantDetail(Tenant tenant) {
      var tenantLogo = tenant.getTenantLogo() != null ? Util.BASE64_ENCODE(tenant.getTenantLogo()) : null;
      this.tenantId = tenant.getTenantId();
      this.tenantType = tenant.getTenantType();
      this.tenantName = tenant.getTenantName();
      this.tenantDID = tenant.getTenantDID();
      this.tenantInvitationUrl = tenant.getTenantInvitationUrl();
      this.tenantStatus = tenant.getTenantStatus();
      this.tenantHomeUrl = tenant.getTenantHomeUrl();
      this.tenantAddress = tenant.getTenantAddress();
      this.tenantLogo = tenantLogo;
      this.trustTenant = tenant.isTrustTenant();
//      this.credentialList = tenant.getCredentialList();
    }
//    public TenantDetail(String tenantId, Tenant.TenantType tenantType, String tenantName, String tenantDID, String tenantInvitationUrl, Tenant.TenantStatus tenantStatus, String tenantHomeUrl, String tenantAddress, String tenantLogo, boolean trustTenant, List<Credential> credentialList) {

//    }
  }

  @Getter
  @ToString
  public static class CredentialDetail {
    private final String credentialId;
    private final String credentialName;
    private final Credential.CredentialType credentialType;
    private final String credentialDefinitionId;
    private final String schemaId;
    private final String authLinkUrl;
    private final String backgroundImg;
    private final String logoImg;

    public CredentialDetail(Credential credential) {
      var backgroundImg = credential.getBackgroundImg() != null ? Util.BASE64_ENCODE(credential.getBackgroundImg()) : null;
      var logoImg = credential.getBackgroundImg() != null ? Util.BASE64_ENCODE(credential.getLogoImg()) : null;

      this.credentialId = credential.getCredentialId();
      this.credentialName = credential.getCredentialName();
      this.credentialType = credential.getCredentialType();
      this.credentialDefinitionId = credential.getCredentialDefinitionId();
      this.schemaId = credential.getSchema().getSchemaId();
      this.authLinkUrl = credential.getAuthLinkUrl();
      this.backgroundImg = backgroundImg;
      this.logoImg = logoImg;
    }
  }




}
