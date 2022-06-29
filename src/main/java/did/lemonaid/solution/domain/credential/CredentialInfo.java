package did.lemonaid.solution.domain.credential;

import did.lemonaid.solution.common.util.Util;
import did.lemonaid.solution.domain.credential.schema.SchemaAttribute;
import did.lemonaid.solution.domain.credential.schema.Schemas;
import lombok.Getter;
import lombok.ToString;

import java.util.List;


public class CredentialInfo {

  @Getter
  @ToString
  public static class CredentialDetail {
    private final String tenantId;
    private final String credentialId;
    private final String credentialName;
    private final Credential.CredentialType credentialType;

    public final String credentialDefinitionId;

    private final String description;
    private final String authLinkUrl;
    private final boolean expiryDateYN;
    private final int validityDays;
    private final String backgroundImg;
    private final String backgroundImgFilename;
    private final String logoImg;
    private final String logoImgFilename;
    private final String templateItemMapping;
    private final String serviceExtensionInfo;
    private final SchemaInfo schema;


    public CredentialDetail(Credential credential, SchemaInfo schemas) {
//      var backgroundImg = credential.getBackgroundImg() != null ? Util.BASE64_ENCODE(credential.getBackgroundImg()) : null;
//      var logoImg = credential.getBackgroundImg() != null ? Util.BASE64_ENCODE(credential.getLogoImg()) : null;

      this.tenantId = credential.getTenant().getTenantId();
      this.credentialId = credential.getCredentialId();
      this.credentialDefinitionId = credential.getCredentialDefinitionId();
      this.credentialName = credential.getCredentialName();
      this.credentialType = credential.getCredentialType();
      this.description = credential.getDescription();
      this.authLinkUrl = credential.getAuthLinkUrl();
      this.expiryDateYN = credential.isExpiryDateYN();
      this.validityDays = credential.getValidityDays();
      this.backgroundImg = credential.getBackgroundImg();
      this.backgroundImgFilename = credential.getBackgroundImgFilename();
      this.logoImg = credential.getLogoImg();
      this.logoImgFilename = credential.getLogoImgFilename();
      this.templateItemMapping = credential.getTemplateItemMapping();
      this.serviceExtensionInfo = credential.getServiceExtensionInfo();
      this.schema = schemas;
    }
  }

  @Getter
  @ToString
  public static class SchemaInfo {
    private  final String schemaId;
    private final String schemaName;
    private final List<SchemaAttributeInfo> schemaAttributeList;

    public SchemaInfo(Schemas schema, List<SchemaAttributeInfo > schemaAttributeList) {
      this.schemaId = schema.getSchemaId();
      this.schemaName = schema.getSchemaName();
      this.schemaAttributeList = schemaAttributeList;
    }
  }

  @Getter
  @ToString
  public static class SchemaAttributeInfo {
    private final String attributeCode;
    private final String attributeName;
    private final String mimeType;

    public SchemaAttributeInfo(SchemaAttribute schemaAttribute) {
      this.attributeCode = schemaAttribute.getAttributeCode();
      this.attributeName = schemaAttribute.getAttributeName();
      this.mimeType = schemaAttribute.getMimeType().getStandardCode();
    }
  }


}
