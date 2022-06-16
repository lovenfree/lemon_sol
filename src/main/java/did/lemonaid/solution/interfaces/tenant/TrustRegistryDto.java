package did.lemonaid.solution.interfaces.tenant;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

public class TrustRegistryDto {

    @Getter
    @Builder
    @ToString
    public static class Tenants {
        private final List<Tenant> tenantList;
    }

    @Getter
    @Builder
    @ToString
    public static class Tenant {
        @JsonProperty("tenant_id")
        @NotNull(message = "필수 파라미터 누락")
        @Schema(name = "Tenant Id", example = "ISSaskdjnek", required = true)
        private final String tenantId;
        @JsonProperty("tenant_type")
        @NotNull(message = "필수 파라미터 누락")
        @Schema(name = "Tenant Type", example = "ISSUER", required = true)
        private final TenantType tenantType;
    }

    @Getter
    @AllArgsConstructor
    public enum TenantType{
        ISSUER("Issuer"), VERIFIER("Verifier"), BOTH("Issuer/Verifier");
        private final String description;
    }



    @Getter
    @Builder
    @ToString
    public static class Certificates {
        private final List<Certificate> certificates;
    }

    @Getter
    @Builder
    @ToString
    public static class Certificate {
        @JsonProperty("tenant_id")
        @NotNull(message = "필수 파라미터 누락")
        @Schema(name = "Tenant Id", example = "ISSaskdjnek", required = true)
        private final String tenantId;
        @JsonProperty("tenant_type")
        @NotNull(message = "필수 파라미터 누락")
        @Schema(name = "Tenant Type", example = "ISSUER", required = true)
        private final TenantType tenantType;
    }


}
