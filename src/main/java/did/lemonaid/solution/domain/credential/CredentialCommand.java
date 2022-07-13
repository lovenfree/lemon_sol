package did.lemonaid.solution.domain.credential;

import did.lemonaid.solution.common.util.TokenGenerator;
import did.lemonaid.solution.domain.credential.schema.SchemaAttribute;
import did.lemonaid.solution.domain.credential.schema.Schemas;
import did.lemonaid.solution.domain.tenant.Tenant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

public class CredentialCommand {
  @Getter
  @ToString
  @AllArgsConstructor
  public static class RegisterCredential {
    private final String credentialName;
    private final  Credential.CredentialType credentialType;
    private final String credentialDefinitionId;
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
    private RegisterSchema schema;
    public Credential toEntity(Tenant tenant, Schemas schema) {
      return Credential.builder()
        .tenant(tenant)
        .schema(schema)
        .credentialName(credentialName)
        .credentialType(credentialType)
        .credentialDefinitionId(credentialDefinitionId)
        .description(description)
        .authLinkUrl(authLinkUrl)
        .expiryDateYN(expiryDateYN)
        .validityDays(validityDays)
        .backgroundImg(backgroundImg)
        .backgroundImgFilename(backgroundImgFilename)
        .logoImg(logoImg)
        .logoImgFilename(logoImgFilename)
        .templateItemMapping(templateItemMapping)
        .serviceExtensionInfo(serviceExtensionInfo)
        .build();
    }
  }

  @Getter
  @ToString
  @AllArgsConstructor
  public static class UpdateCredential {
    private final String credentialName;
    private final  Credential.CredentialType credentialType;
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
  }

  @Getter
  @ToString
  @AllArgsConstructor
  public static class RegisterSchema {
    private final String schemaId;
    private final String schemaName;
    private final List<RequestSchemaAttribute> schemaAttributeList;

    public Schemas toEntity() {
      return Schemas.builder()
        .schemaId(schemaId)
        .schemaName(schemaName)
        .build();
    }
  }


  @Getter
  @AllArgsConstructor
  @ToString
  public static class RequestSchemaAttribute {
    private final String attributeCode;
    private final String attributeName;
    private final String mimeType;

    public SchemaAttribute toEntity(Schemas schemas){
      return SchemaAttribute.builder()
        .attributeCode(attributeCode)
        .attributeName(attributeName)
        .mimeType(mimeType)
        .schema(schemas)
        .build();
    }
  }

  @Getter
  @AllArgsConstructor
  @ToString
  public static class UpdateCredentialStatus {
    private final String credentialId;
    private final boolean trustCredentialYN;
  }
}
