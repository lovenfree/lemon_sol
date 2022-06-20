package did.lemonaid.solution.domain.credential;

import did.lemonaid.solution.domain.credential.schema.Schemas;
import lombok.Builder;
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

    public static String credentialDefinitionId;

    private final String description;
    private final String authLinkUrl;
    private final boolean expiryDateYN;
    private final int validityDays;
    private final String backgroundImg;
    private final String logoImg;
    private final String tempItemMapping;
    private final SchemaInfo schema;


    public CredentialDetail(String tenantId, String credentialId, String credentialName, Credential.CredentialType credentialType, String description, String authLinkUrl, boolean expiryDateYN, int validityDays, String backgroundImg, String logoImg, String tempItemMapping, SchemaInfo schema) {
      this.tenantId = tenantId;
      this.credentialId = credentialId;
      this.credentialName = credentialName;
      this.credentialType = credentialType;
      this.description = description;
      this.authLinkUrl = authLinkUrl;
      this.expiryDateYN = expiryDateYN;
      this.validityDays = validityDays;
      this.backgroundImg = backgroundImg;
      this.logoImg = logoImg;
      this.tempItemMapping = tempItemMapping;
      this.schema = schema;
    }
  }

  @Getter
  @ToString
  public static class SchemaInfo {
    private  final String schemaId;
    private final String schemaName;
    private final List<SchemaAttributeInfo> schemaAttributeList;

    public SchemaInfo(String schemaId, String schemaName, List<SchemaAttributeInfo > schemaAttributeList) {
      this.schemaId = schemaId;
      this.schemaName = schemaName;
      this.schemaAttributeList = schemaAttributeList;
    }
  }

  @Getter
  @ToString
  public static class SchemaAttributeInfo {
    private final String attributeCode;
    private final String attributeName;
    private final SchemaAttribute.MimeType mimeType;

    public SchemaAttributeInfo(String attributeCode, String attributeName, SchemaAttribute.MimeType mimeType) {
      this.attributeCode = attributeCode;
      this.attributeName = attributeName;
      this.mimeType = mimeType;
    }
  }


}
