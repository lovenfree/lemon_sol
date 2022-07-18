package did.lemonaid.solution.interfaces.trustregistry.tenant;

import com.fasterxml.jackson.annotation.JsonProperty;
import did.lemonaid.solution.domain.credential.Credential;
import did.lemonaid.solution.domain.tenant.Tenant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

public class TRTenantDto {

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

    @Schema(description = "Tenant invitation URL", example = "https://agency-dev-lemonaid.singlex.com?...")
    private final String tenantInvitationUrl;

    @Schema(description = "Tenant Status", example = "Activate")
    private final Tenant.TenantStatus tenantStatus;

    @Schema(description = "Tenant logo path(base64 image)", example = "")
    private final String tenantLogo;
    @Schema(description = "Tenant logo filename", example = "lgcns.png")
    private final String tenantLogoFilename;

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
    @Schema(description = "Tenant Invitation Url", example = "https://agency-dev-lemonaid.singlex.com?...", required = true)
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
  @Builder
  @ToString
  public static class Credentials {
    @JsonProperty("credentials")
    private final List<TRTenantDto.CredentialInfo> credentialInfos;
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
}
