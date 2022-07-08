package did.lemonaid.solution.interfaces.credential;

import com.fasterxml.jackson.annotation.JsonProperty;
import did.lemonaid.solution.domain.credential.Credential;
import did.lemonaid.solution.interfaces.trustregistry.credential.TRCredentialDto;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class CredentialDto {

  @Getter
  @Builder
  @ToString
  public static class CredentialSearchCondition{
    @Parameter(description = "Tenant DID", example = "did:lem:038dhskjesldkfah")
    private String tenantDID;
    @Parameter(description = "Credential Name", example = "LG EMP ID")
    private  String credentialName;
    @Parameter(description = "Credential Type", example = "ID")
    private Credential.CredentialType credentialType;
    @Parameter(description = "Credential Definition ID", example = "credentamnxjdhfasf:1.0")
    private  String credentialDefinitionId;
    @Parameter(description = "expiry Date YN", example = "true")
    private boolean expiryDateYN;
    @Parameter(description = "trust credential status", example = "true")
    private boolean trustCredentialYN;
  }

  @Getter
  @Builder
  @ToString
  public static class Credentials {
    @JsonProperty("credentials")
    private final List<CredentialInfo> credentialInfos;
  }
  //credential List
  @Getter
  @Setter
  @ToString
  public static class CredentialInfo {
    @Schema(description = "Tenant DID", example = "did:lem:038dhskjesldkfah")
    private String tenantDID;
    @Schema(description = "Tenant Name", example = "did:lem:038dhskjesldkfah")
    private String tenantName;
    @Schema(description = "Credential Name", example = "LG EMP ID")
    private  String credentialName;
    @Schema(description = "Credential Type", example = "ID")
    private Credential.CredentialType credentialType;
    @Schema(description = "Credential Definition ID", example = "credentamnxjdhfasf:1.0")
    private  String credentialDefinitionId;
    @Schema(description = "expiry Date YN", example = "true", required = true)
    @NotNull(message = "필수 파라미터 누락")
    private boolean expiryDateYN;
    @Schema(description = "validity Days", example = "360")
    private int validityDays;
    @Schema(description = "trust credential status", example = "true")
    private boolean trustCredentialYN;
    @Schema(description = "Schema Id", example = "schemasjkjfknxkjkd:1.0")
    private  String schemaId;
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
    private final SchemaDto.Main schema;
  }



  @Getter
  @AllArgsConstructor
  public static class CredentialResponse {
    private final String credentialId;

  }


  @Getter
  @ToString
  @Builder
  public static class CredentialAdminDetail {
    @Schema(description = "Tenant ID", example = "slkjdkglajksejrhjgaskj")
    private final String tenantId;
    @Schema(description = "Tenant Name", example = "slkjdkglajksejrhjgaskj")
    private final String tenantName;
    @Schema(description = "Tenant DID", example = "slkjdkglajksejrhjgaskj")
    private final String tenantDID;
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
    private final String backgroundImg;
    @Schema(description = "Background Image file name", example = "background.jpg")
    private final String backgroundImgFilename;
    @Schema(description = "Logo Image", example = "alskdjgkalsj")
    private final String logoImg;
    @Schema(description = "Logo Image Filename", example = "logo.img")
    private final String logoImgFilename;
    @Schema(description = "credential template Info", example = "...")
    private final String templateItemMapping;
    @Schema(description = "credential extension information", example = "")
    private final String serviceExtensionInfo;
    @Schema(description = "Schema")
    private final SchemaDto.Main schema;
  }

  @Getter
  @Setter
  @ToString
  public class UpdateCredentialStatus {

  }
}
