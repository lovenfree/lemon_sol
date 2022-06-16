package did.lemonaid.solution.interfaces.trustregistry;

import did.lemonaid.solution.domain.tenant.Tenant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

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
    public static class Credentials {
        private final List<Credential> credentials;
    }

    @Getter
    @Builder
    @ToString
    public static class Credential {
        @Schema(description = "Credential Id", example = "lgcnsudskjd", required = true)
        private final String credentialId;
        @Schema(description = "Credential Name", example = "LG EMP ID", required = true)
        private final String credentialName;
        @Schema(description = "Credential Name", example = "dkjsjebjbvkdjjal", required = true)
        private final String credentialDefinitionId;
        @Schema(description = "Schema Id", example = "schlakdsjfkeknk", required = true)
        private final String schemaId;
        @Schema(description = "Issue Auth URL", example = "http://lil.lgcns.com", required = true)
        private final String authLinkUrl;
        @Schema(description = "Background Image", example = "background.jpg", required = true)
        private final String backgroundImg;
        @Schema(description = "Logo Image", example = "logo.img", required = true)
        private final String logoImg;
    }


}
