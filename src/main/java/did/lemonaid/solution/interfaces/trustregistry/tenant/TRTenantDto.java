package did.lemonaid.solution.interfaces.trustregistry.tenant;

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
}
