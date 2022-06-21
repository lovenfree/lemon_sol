package did.lemonaid.solution.interfaces.trustregistry;

import did.lemonaid.solution.domain.credential.Credential;
import did.lemonaid.solution.domain.credential.schema.SchemaAttribute;
import did.lemonaid.solution.domain.tenant.Tenant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

public class TrustRegistryDto {

    @Getter
    @Builder
    @ToString
    public static class Tenants {
        private final List<TenantInfo> tenants;
    }

    @Getter
    @Builder
    @ToString
    public static class TenantInfo {

        @Schema(description = "Tenant Id", example = "tnt_askjeigjksldkfjh")
        private final String tenantId;

        @Schema(description = "Tenant Type", example = "ISSUER")
        private final Tenant.TenantType tenantType;

        @Schema(description = "Tenant Name", example = "LGCNS ISSUER")
        private final String tenantName;

        @Schema(description = "Tenant DID", example = "did:lem:038dhskjesldkfah")
        private final String tenantDID;

        @Schema(description = "Tenant invitation URL", example = "https://ajskdjfkalskejk.claksjdlaksdfjlaks...")
        private final String tenantInvitationUrl;

        @Schema(description = "Tenant Status", example = "Activate")
        private final Tenant.TenantStatus tenantStatus;

        @Schema(description = "Tenant logo path(base64 image)", example = "")
        private final String tenantLogo;

    }



    @Getter
    @Builder
    @ToString
    public static class Credentials {
        private final List<CredentialInfo> credentialInfos;
    }

    @Getter
    @Builder
    @ToString
    public static class CredentialInfo {
        @Schema(description = "Credential Id", example = "TNT_tjalkjaslkddjxc")
        private final String credentialId;
        @Schema(description = "Credential Name", example = "LG EMP ID")
        private final String credentialName;
        @Schema(description = "Credential Type", example = "ID")
        private final Credential.CredentialType credentialType;
        @Schema(description = "Credential Definition ID", example = "credentamnxjdhfasf:1.0")
        private final String credentialDefinitionId;
        @Schema(description = "Schema Id", example = "schemasjkjfknxkjkd:1.0")
        private final String schemaId;
        @Schema(description = "Issue Auth URL", example = "http://lil.lgcns.com")
        private final String authLinkUrl;
        @Schema(description = "Background Image", example = "background.jpg")
        private final String backgroundImg;
        @Schema(description = "Logo Image", example = "logo.img")
        private final String logoImg;
    }

    //credential 등록
  @Getter
  @Setter
  @ToString
  public static class RegisterCredentialRequest {
      @Schema(description = "Tenant ID", example = "slkjdkglajksejrhjgaskj", required = true)
      private String tenantId;
      @Schema(description = "Credential Id", example = "lgcnsudskjd", required = true)
      private  String credentialId;
      @Schema(description = "Credential Name", example = "LG EMP ID", required = true)
      private  String credentialName;
      @Schema(description = "Credential Type", example = "ID", required = true)
      private Credential.CredentialType credentialType;
      @Schema(description = "Credential Definition ID", example = "credentamnxjdhfasf:1.0", required = true)
      private  String credentialDefinitionId;

      @Schema(description = "Description", example = "DID id credential")
      private  String description;
      @Schema(description = "User Auth Page", example = "http://lil.lgcns.com", required = true)
      private String authLinkUrl;
      @Schema(description = "expiry Date YN", example = "true", required = true)
      private boolean expiryDateYN;
      @Schema(description = "validity Days", example = "360")
      private int validityDays;
      @Schema(description = "Background Image", example = "background.jpg")
      private String backgroundImg;
      @Schema(description = "Logo Image", example = "logo.img")
      private String logoImg;
      @Schema(description = "???", example = "???", required = true)
      private String tempItemMapping;

      private SchemaInfo schemaInfo;


  }

  @Getter
  @Setter
  @ToString
  public static class SchemaInfo {
    @Schema(description = "Schema Id", example = "schemasjkjfknxkjkd:1.0")
    private  String schemaId;
    @Schema(description = "Schema Name", example = "사원증")
    private String schemaName;
    @Schema(description = "Schema attribute List")
    private List<RequestSchemaAttribute> schemaAttributeList;
  }



  @Getter
  @Setter
  @ToString
  public static class RequestSchemaAttribute {
    @Schema(description = "Schema Attribute Code", example = "name", required = true)
    private String attributeCode;
    @Schema(description = "Schema attribute Name", example = "name", required = true)
    private String attributeName;
    @Schema(description = "Schema attribute Mime Type", example = "String", required = true)
    private SchemaAttribute.MimeType mimeType;
  }


  @Getter
  @Builder
  @ToString
  public static class CredentialResponse {
    private final String credentialId;
  }

  @Getter
  @Builder
  @ToString
  public static class CredentialDetail {
    @Schema(description = "Tenant ID", example = "slkjdkglajksejrhjgaskj", required = true)
    private final String tenantId;
    @Schema(description = "Credential Id", example = "lgcnsudskjd", required = true)
    private final String credentialId;
    @Schema(description = "Credential Name", example = "LG EMP ID", required = true)
    private final String credentialName;
    @Schema(description = "Credential Type", example = "ID", required = true)
    private final Credential.CredentialType credentialType;
    @Schema(description = "Credential Definition ID", example = "credentamnxjdhfasf:1.0", required = true)
    private final String credentialDefinitionId;

    @Schema(description = "Description", example = "DID id credential")
    private final String description;
    @Schema(description = "User Auth Page", example = "http://lil.lgcns.com", required = true)
    private final String authLinkUrl;
    @Schema(description = "expiry Date YN", example = "true", required = true)
    private final boolean expiryDateYN;
    @Schema(description = "validity Days", example = "360")
    private final int validityDays;
    @Schema(description = "Background Image", example = "background.jpg")
    private final String backgroundImg;
    @Schema(description = "Logo Image", example = "logo.img")
    private final String logoImg;
    @Schema(description = "credential template Info", example = "???", required = true)
    private final String tempItemMapping;

    @Schema(description = "Schema", required = true)
    private final SchemaDto schema;

//    @Schema(description = "Schema Id", example = "schemasjkjfknxkjkd:1.0", required = true)
//    private  final String schemaId;
//    @Schema(description = "Schema Name", example = "사원증", required = true)
//    private final String schemaName;
//    @Schema(description = "Schema attribute List", required = true)
//    private final List<SchemaAttributeDto> schemaAttributeList;

  }

  @Getter
  @Builder
  @ToString
  public static class SchemaDto {
    @Schema(description = "Schema Id", example = "schemasjkjfknxkjkd:1.0", required = true)
    private  final String schemaId;
    @Schema(description = "Schema Name", example = "사원증", required = true)
    private final String schemaName;
    @Schema(description = "Schema attribute List", required = true)
    private final List<SchemaAttributeDto> schemaAttributeList;
  }


  @Getter
  @Builder
  @ToString
  public static class SchemaAttributeDto {
    @Schema(description = "Schema Attribute Code", example = "name", required = true)
    private final String attributeCode;
    @Schema(description = "Schema attribute Name", example = "name", required = true)
    private final String attributeName;
    @Schema(description = "Schema attribute Mime Type", example = "String", required = true)
    private final SchemaAttribute.MimeType mimeType;
  }

}
