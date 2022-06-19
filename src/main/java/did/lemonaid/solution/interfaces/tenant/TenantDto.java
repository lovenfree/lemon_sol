package did.lemonaid.solution.interfaces.tenant;

import did.lemonaid.solution.domain.tenant.Tenant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

public class TenantDto {

  @Getter
  @Builder
  @ToString
  public static class RegisterTenantRequest {
    private final Tenant.TenantType tenantType;
    private final String tenantName;
    private final String tenantHomeUrl;
    private final String tenantAddress;
    private final String tenantLogoPath;
  }

  @Getter
  @Builder
  @ToString
  public static class UpdateTenantRequest {
    private final Tenant.TenantType tenantType;
    private final String tenantName;
    private final String tenantDID;
    private final String tenantInvitationUrl;
    private final Tenant.TenantStatus tenantStatus;
    private final String tenantHomeUrl;
    private final String tenantAddress;
    private final String tenantLogoPath;
  }


  @Getter
  @Builder
  @ToString
  public static class ActivateTenantRequest {
    private final String tenantDID;
    private final String tenantInvitationUrl;
  }


  @Getter
  @Builder
  @ToString
  public static class Tenants {
    private final List<TenantDto.TenantInfo> tenants;
  }

  @Getter
  @Builder
  @ToString
  public static class TenantInfo {

    @Schema(description = "Tenant Id", example = "ISSaskdjnek", required = true)
    private final String tenantId;

    @Schema(description = "Tenant Type", example = "ISSUER", required = true)
    private final Tenant.TenantType tenantType;

    @Schema(description = "Tenant Name", example = "ISSUER", required = true)
    private final String tenantName;

    @Schema(description = "Tenant DID", example = "did:lem:038dhskjesldkfah")
    private final String tenantDID;

    @Schema(description = "Tenant invitation URL", example = "did:lem:038dhskjesldkfah")
    private final String tenantInvitationUrl;

    @Schema(description = "Tenant Status", example = "Activate", required = true)
    private Tenant.TenantStatus tenantStatus;

    @Schema(description = "Tenant logo path", example = "/salksdjf/alskdjfkjlj.jpg", required = true)
    private String tenantLogoPath;

  }

  @Getter
  @Builder
  @ToString
  public static class RegisterResponse {
    private final String tokenId;
  }

  @Getter
  @Builder
  @ToString
  public static class ActivateResponse {
    private final String tokenId;
  }

  @Getter
  @Builder
  @ToString
  public static class UpdateResponse {
    private final String tokenId;
  }

}
