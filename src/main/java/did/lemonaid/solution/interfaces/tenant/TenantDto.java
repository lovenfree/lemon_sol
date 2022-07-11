package did.lemonaid.solution.interfaces.tenant;

import com.fasterxml.jackson.annotation.JsonFormat;
import did.lemonaid.solution.domain.tenant.Tenant;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class TenantDto {

  @Getter
  @Setter
  @ToString
  public static class RegisterTenantRequest {
    @NotNull(message = "필수 파라미터 누락")
    @Schema(description = "Tenant Type", example = "ISSUER", required = true)
    private Tenant.TenantType tenantType;
    @NotBlank(message = "필수 파라미터 누락")
    @Schema(description = "Tenant Name", example = "LGCNS ISSUER", required = true)
    private String tenantName;
    @Schema(description = "Tenant Homepage URL", example = "www.lgcns.com")
    private String tenantHomeUrl;
    @Schema(description = "Tenant address", example = "서울 강서구 마곡로")
    private String tenantAddress;
    @Schema(description = "Tenant logo image(base64)", example = "...")
    private String tenantLogo;
    @Schema(description = "Tenant logo image file name & extension", example = "loge.png")
    private String tenantLogoFilename;
  }


  @Getter
  @Setter
  @ToString
  public static class UpdateTenantRequest {
    @NotNull(message = "필수 파라미터 누락")
    @Schema(description = "Tenant Type", example = "ISSUER", required = true)
    private Tenant.TenantType tenantType;
    @NotBlank(message = "필수 파라미터 누락")
    @Schema(description = "Tenant Name", example = "LGCNS ISSUER", required = true)
    private String tenantName;
//    @Schema(description = "Tenant DID", example = "RTHAnR3aKM5iSNmHnr4am4")
//    private String tenantDID;
//    @Schema(description = "Tenant Wallet ID", example = "ID")
//    private String tenantWalletId;
//    @Schema(description = "Tenant Invitation Url", example = "https://devacapyinbound.duckdns.org? ...")
//    private String tenantInvitationUrl;
    @NotNull(message = "필수 파라미터 누락")
    @Schema(description = "Tenant Status", example = "ACTIVATE", required = true)
    private Tenant.TenantStatus tenantStatus;
    @Schema(description = "Tenant Homepage URL", example = "www.lgcns.com")
    private String tenantHomeUrl;
    @Schema(description = "Tenant address", example = "서울 강서구 마곡로")
    private String tenantAddress;
    @Schema(description = "Tenant logo image(base64)", example = "...")
    private String tenantLogo;
    @Schema(description = "Tenant logo image file name & extension", example = "loge.png")
    private String tenantLogoFilename;
  }

  public static class changeTenantTrustStatus{
    @Schema(description = "Trust Tenant Y/N", example = "true", required = true)
    private Boolean trustTenant;
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
    @Schema(description = "Tenant Id", example = "ISSaskdjnek")
    private final String tenantId;

    @Schema(description = "Tenant Type", example = "ISSUER")
    private final Tenant.TenantType tenantType;

    @Schema(description = "Tenant Name", example = "ISSUER")
    private final String tenantName;

    @Schema(description = "Tenant DID", example = "did:lem:038dhskjesldkfah")
    private final String tenantDID;

    @Schema(description = "Tenant Wallet ID", example = "")
    private final String tenantWalletId;

    @Schema(description = "Tenant invitation URL", example = "did:lem:038dhskjesldkfah")
    private final String tenantInvitationUrl;

    @Schema(description = "Tenant Status", example = "Activate")
    private final Tenant.TenantStatus tenantStatus;

    @Schema(description = "Tenant Home URL", example = "http://lemonaid.com")
    private final String tenantHomeUrl;

    @Schema(description = "Tenant Address", example = "서울시 강서구 마곡")
    private final String tenantAddress;

    @Schema(description = "Tenant logo img", example = "asdfldd...")
    private final String tenantLogo;

    @Schema(description = "Tenant logo filename", example = "lgcnsLogo.png")
    private final String tenantLogoFilename;

    @Schema(description = "Revised Date", example="2021-01-01 00:00:00")
    private final LocalDateTime revisedDate;

  }

  @Getter
  @Builder
  @ToString
  public static class TenantResponse {
    @Schema(description = "Tenant Id", example = "ISSaskdjnek")
    private final String tenantId;
  }

  @Getter
  @Setter
  @ToString
  public static class TenantSearchCondition{
    @Parameter(description = "Tenant Type",  example = "ISSUER")
    private Tenant.TenantType tenantType;
    @Parameter(description = "Tenant Name", example = "ISSUER")
    private String tenantName;
    @Parameter(description = "Tenant Status", example = "ACTIVATE")
    private Tenant.TenantStatus tenantStatus;
    @Parameter(description = "Tenant DID", example = "did:lem:038dhskjesldkfah")
    private String tenantDID;
    @Schema(description="search start date", example="2021-01-01 00:00:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime searchStartDate;
    @Schema(description="search end date", example="2022-12-31 23:59:59")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime searchEndDate;
  }


}
