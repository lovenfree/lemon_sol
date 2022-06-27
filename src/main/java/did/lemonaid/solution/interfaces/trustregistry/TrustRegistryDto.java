package did.lemonaid.solution.interfaces.trustregistry;

import com.fasterxml.jackson.annotation.JsonProperty;
import did.lemonaid.solution.domain.credential.Credential;
import did.lemonaid.solution.domain.credential.schema.SchemaAttribute;
import did.lemonaid.solution.domain.tenant.Tenant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

        @Schema(description = "Tenant Wallet ID", example = "xxidxkdijg")
        private final String tenantWalletId;

        @Schema(description = "Tenant invitation URL", example = "https://ajskdjfkalskejk.claksjdlaksdfjlaks...")
        private final String tenantInvitationUrl;

        @Schema(description = "Tenant Status", example = "Activate")
        private final Tenant.TenantStatus tenantStatus;

        @Schema(description = "Tenant logo path(base64 image)", example = "")
        private final String tenantLogo;
        @Schema(description = "Tenant logo filename", example = "lgcns.png")
        private final String tenantLogoFilename;

    }

    @Getter
    @Builder
    @ToString
    public static class Credentials {
        @JsonProperty("credentials")
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
        @Schema(description = "Logo Image", example = "alskdjgkalsj")
        private String logoImg;
        @Schema(description = "Logo Image Filename", example = "logo.img")
        private String logoImgFilename;
    }

    //credential 등록
  @Getter
  @Setter
  @Builder
  @ToString
  public static class RegisterCredentialRequest {
      @Schema(description = "Tenant ID", example = "slkjdkglajksejrhjgaskj", required = true)
      @NotBlank(message = "필수 파라미터 누락")
      private String tenantId;
      @Schema(description = "Credential Id", example = "lgcnsudskjd", required = true)
      @NotBlank(message = "필수 파라미터 누락")
      private  String credentialId;
      @Schema(description = "Credential Name", example = "LG EMP ID", required = true)
      @NotBlank(message = "필수 파라미터 누락")
      private  String credentialName;
      @Schema(description = "Credential Type", example = "ID", required = true)
      @NotNull(message = "필수 파라미터 누락")
      private Credential.CredentialType credentialType;
      @Schema(description = "Credential Definition ID", example = "credentamnxjdhfasf:1.0", required = true)
      @NotBlank(message = "필수 파라미터 누락")
      private  String credentialDefinitionId;
      @Schema(description = "Description", example = "DID id credential")
      private  String description;
      @Schema(description = "User Auth Page", example = "http://lil.lgcns.com", required = true)
      @NotBlank(message = "필수 파라미터 누락")
      private String authLinkUrl;
      @Schema(description = "expiry Date YN", example = "true", required = true)
      @NotNull(message = "필수 파라미터 누락")
      private boolean expiryDateYN;
      @Schema(description = "validity Days", example = "360")
      private int validityDays;
      @Schema(description = "Background Image", example = "asldjgkals")
      private String backgroundImg;
      @Schema(description = "Background Image file name", example = "background.jpg")
      private String backgroundImgFilename;
      @Schema(description = "Logo Image", example = "alskdjgkalsj")
      private String logoImg;
      @Schema(description = "Logo Image Filename", example = "logo.img")
      private String logoImgFilename;
      @Schema(description = "credential template mapping info", example = "???", required = true)
      @NotBlank(message = "필수 파라미터 누락")
      private String templateItemMapping;
      @Schema(description = "Schema Info", required = true)
      @NotNull(message = "필수 파라미터 누락")
      private TrustRegistryDto.RegisterSchemaInfo schema;
  }

  @Getter
  @Setter
  @ToString
  public static class UpdateCredentialRequest {
    @Schema(description = "Credential Name", example = "LG EMP ID", required = true)
    @NotBlank(message = "필수 파라미터 누락")
    private  String credentialName;
    @Schema(description = "Credential Type", example = "ID", required = true)
    @NotNull(message = "필수 파라미터 누락")
    private Credential.CredentialType credentialType;
    @Schema(description = "Description", example = "DID id credential")
    private  String description;
    @Schema(description = "User Auth Page", example = "http://lil.lgcns.com", required = true)
    @NotBlank(message = "필수 파라미터 누락")
    private String authLinkUrl;
    @Schema(description = "expiry Date YN", example = "true", required = true)
    @NotBlank(message = "필수 파라미터 누락")
    private boolean expiryDateYN;
    @Schema(description = "validity Days", example = "360")
    private int validityDays;
    @Schema(description = "Background Image", example = "asldjgkals")
    private String backgroundImg;
    @Schema(description = "Background Image file name", example = "background.jpg")
    private String backgroundImgFilename;
    @Schema(description = "Logo Image", example = "alskdjgkalsj")
    private String logoImg;
    @Schema(description = "Logo Image Filename", example = "logo.img")
    private String logoImgFilename;
    @Schema(description = "Template Mapping Info", example = "???", required = true)
    @NotBlank(message = "필수 파라미터 누락")
    private String templateItemMapping;
  }

  @Getter
  @Setter
  @Builder
  @ToString
  public static class ActivateTenantRequest {
    @Schema(description = "Tenant DID", example = "RTHAnR3aKM5iSNmHnr4am4", required = true)
    @NotNull(message = "필수 파라미터 누락")
    private String tenantDID;
    @Schema(description = "Tenant Wallet ID", example = "ID", required = true)
    @NotNull(message = "필수 파라미터 누락")
    private String tenantWalletId;
    @Schema(description = "Tenant Invitation Url", example = "https://devacapyinbound.duckdns.org? ...", required = true)
    @NotNull(message = "필수 파라미터 누락")
    private String tenantInvitationUrl;
  }

  @Getter
  @Builder
  @ToString
  public static class TenantResponse {
    private final String tenantId;
  }



  @Getter
  @Setter
  @Builder
  @ToString
  public static class RegisterSchemaInfo {
    @Schema(description = "Schema Id", example = "schemasjkjfknxkjkd:1.0", required = true)
    @NotBlank(message = "필수 파라미터 누락")
    private  String schemaId;
    @Schema(description = "Schema Name", example = "사원증", required = true)
    @NotBlank(message = "필수 파라미터 누락")
    private String schemaName;
    @Schema(description = "Schema attribute List", required = true)
    @NotNull(message = "필수 파라미터 누락")
    private List<RegisterSchemaAttribute> schemaAttributeList;
  }



  @Getter
  @Setter
  @Builder
  @ToString
  public static class RegisterSchemaAttribute {
    @Schema(description = "Schema Attribute Code", example = "name", required = true)
    @NotBlank(message = "필수 파라미터 누락")
    private String attributeCode;
    @Schema(description = "Schema attribute Name", example = "name", required = true)
    @NotBlank(message = "필수 파라미터 누락")
    private String attributeName;
    @Schema(description = "Schema attribute Mime Type", example = "String", required = true)
    @NotNull(message = "필수 파라미터 누락")
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
    @Schema(description = "Tenant ID", example = "slkjdkglajksejrhjgaskj")
    private final String tenantId;
    @Schema(description = "Credential Id", example = "lgcnsudskjd")
    private final String credentialId;
    @Schema(description = "Credential Name", example = "LG EMP ID")
    private final String credentialName;
    @Schema(description = "Credential Type", example = "ID")
    private final Credential.CredentialType credentialType;
    @Schema(description = "Credential Definition ID", example = "credentamnxjdhfasf:1.0")
    private final String credentialDefinitionId;

    @Schema(description = "Description", example = "DID id credential")
    private final String description;
    @Schema(description = "User Auth Page", example = "http://lil.lgcns.com")
    private final String authLinkUrl;
    @Schema(description = "expiry Date YN", example = "true")
    private final boolean expiryDateYN;
    @Schema(description = "validity Days", example = "360")
    private final int validityDays;
    @Schema(description = "Background Image", example = "asldjgkals")
    private String backgroundImg;
    @Schema(description = "Background Image file name", example = "background.jpg")
    private String backgroundImgFilename;
    @Schema(description = "Logo Image", example = "alskdjgkalsj")
    private String logoImg;
    @Schema(description = "Logo Image Filename", example = "logo.img")
    private String logoImgFilename;
    @Schema(description = "credential template Info", example = "...")
    private final String templateItemMapping;

    @Schema(description = "Schema")
    private final SchemaDto schema;

  }

  @Getter
  @Builder
  @ToString
  public static class SchemaDto {
    @Schema(description = "Schema Id", example = "schemasjkjfknxkjkd:1.0")
    private  final String schemaId;
    @Schema(description = "Schema Name", example = "사원증")
    private final String schemaName;
    @Schema(description = "Schema attribute List")
    private final List<SchemaAttributeDto> schemaAttributeList;
  }


  @Getter
  @Builder
  @ToString
  public static class SchemaAttributeDto {
    @Schema(description = "Schema Attribute Code", example = "name")
    private final String attributeCode;
    @Schema(description = "Schema attribute Name", example = "name")
    private final String attributeName;
    @Schema(description = "Schema attribute Mime Type", example = "String")
    private final SchemaAttribute.MimeType mimeType;
  }

}
