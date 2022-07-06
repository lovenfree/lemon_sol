package did.lemonaid.solution.interfaces.trustregistry.credential;

import com.fasterxml.jackson.annotation.JsonProperty;
import did.lemonaid.solution.domain.credential.Credential;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class TRCredentialDto {

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
    @Schema(description = "Description", example = "DID id credential")
    private  String description;
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
    @Schema(description = "Tenant ID", example = "tnt_0GmNAEKCVqjQU6g4", required = true)
    @NotBlank(message = "필수 파라미터 누락")
    private String tenantId;
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
    @Schema(description = "service Extension Info", example = "???")
    private String serviceExtensionInfo;
    @Schema(description = "Schema Info", required = true)
    @NotNull(message = "필수 파라미터 누락")
    private TRCredentialDto.RegisterSchemaInfo schema;
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
    @Schema(description = "Schema attribute Mime Type", example = "TEXT_PLAIN", required = true)
    @NotNull(message = "필수 파라미터 누락")
    private String mimeType;
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
    @Schema(description = "Template Mapping Info", example = "???", required = true)
    @NotBlank(message = "필수 파라미터 누락")
    private String templateItemMapping;
    @Schema(description = "credential extension information", example = "")
    private String serviceExtensionInfo;
  }


  @Getter
  @Builder
  @AllArgsConstructor
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
    @Schema(description = "credential extension information", example = "")
    private String serviceExtensionInfo;
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
    @Schema(description = "Schema attribute Mime Type", example = "TEXT/PLAIN")
    private final String mimeType;
  }
}
