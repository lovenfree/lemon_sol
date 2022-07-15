package did.lemonaid.solution.interfaces.credential;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import did.lemonaid.solution.domain.credential.Credential;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class CredentialDto {

  @Getter
  @Setter
  @ToString
  public static class UpdateCredentialStatus {
    @Schema(description = "Credential ID", example = "")
    private String credentialId;
    @Parameter(description = "trust credential status", example = "true")
    private boolean trustCredentialYN;
  }

  @Getter
  @Setter
  @ToString
  public static class CredentialSearchCondition{
    @Parameter(description = "Tenant Name", example = "Tnt_alskdjfkajlskdjf")
    private String tenantName;
    @Parameter(description = "Credential Name", example = "LG EMP ID")
    private  String credentialName;
    @Parameter(description = "Credential Type", example = "ID")
    private Credential.CredentialType credentialType;
    @Parameter(description = "Credential Definition ID", example = "credentamnxjdhfasf:1.0")
    private  String credentialDefinitionId;
    @Parameter(description = "expiry Date YN", example = "true")
    private Boolean expiryDateYN;
    @Parameter(description = "trust credential status", example = "true")
    private Boolean trustCredentialYN;
    @Schema(description="search start date", example="2021-01-01 00:00:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime searchStartDate;
    @Schema(description="search end date", example="2022-12-31 23:59:59")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime searchEndDate;
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
  @Builder
  @ToString
  public static class CredentialInfo {
    @Schema(description = "Tenant DID", example = "did:lem:038dhskjesldkfah")
    private final String tenantDID;
    @Schema(description = "Tenant Name", example = "did:lem:038dhskjesldkfah")
    private final String tenantName;
    @Schema(description = "Credential ID", example = "")
    private final String credentialId;
    @Schema(description = "Credential Name", example = "LG EMP ID")
    private final String credentialName;
    @Schema(description = "Credential Type", example = "ID")
    private final Credential.CredentialType credentialType;
    @Schema(description = "Credential Definition ID", example = "credentamnxjdhfasf:1.0")
    private final String credentialDefinitionId;
    @Schema(description = "expiry Date YN", example = "true", required = true)
    @NotNull(message = "필수 파라미터 누락")
    private final boolean expiryDateYN;
    @Schema(description = "validity Days", example = "360")
    private final int validityDays;
    @Schema(description = "trust credential status", example = "true")
    private final boolean trustCredentialYN;
    @Schema(description = "Schema Id", example = "schemasjkjfknxkjkd:1.0")
    private final String schemaId;
    @Schema(description = "Revised Date", example="2021-01-01 00:00:00")
    private final LocalDateTime revisedDate;
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
  @Builder
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
    @Schema(description = "Trust Credential YN", example = "true")
    private final boolean trustCredentialYN;
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


}
