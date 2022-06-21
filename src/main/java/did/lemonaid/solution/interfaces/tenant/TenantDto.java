package did.lemonaid.solution.interfaces.tenant;

import did.lemonaid.solution.domain.tenant.Tenant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

public class TenantDto {

  @Getter
  @Setter
  @ToString
  public static class RegisterTenantRequest {
    private Tenant.TenantType tenantType;
    private String tenantName;
    private String tenantHomeUrl;
    private String tenantAddress;
    private String tenantLogo;
  }

  @Getter
  @Setter
  @ToString
  public static class UpdateTenantRequest {
    private Tenant.TenantType tenantType;
    private String tenantName;
    private String tenantDID;
    private String tenantInvitationUrl;
    private Tenant.TenantStatus tenantStatus;
    private String tenantHomeUrl;
    private String tenantAddress;
    private String tenantLogo;
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
    private final Tenant.TenantStatus tenantStatus;

    @Schema(description = "Tenant Home URL", example = "http://lemonaid.com")
    private final String tenantHomeUrl;

    @Schema(description = "Tenant Address", example = "서울시 강서구 마곡")
    private final String tenantAddress;

    @Schema(description = "Tenant logo path", example = "/salksdjf/alskdjfkjlj.jpg")
    private final String tenantLogo;

  }

  @Getter
  @Builder
  @ToString
  public static class TenantResponse {
    private final String tenantId;
  }



}
