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

        @Schema(name = "Tenant Id", example = "ISSaskdjnek", required = true)
        private final String tenantId;

        @Schema(name = "Tenant Type", example = "ISSUER", required = true)
        private final Tenant.TenantType tenantType;

        @Schema(name = "Tenant Name", example = "ISSUER", required = true)
        private final String tenantName;

        @Schema(name = "Tenant DID", example = "did:lem:038dhskjesldkfah")
        private final String tenantDID;

        @Schema(name = "Tenant invitation URL", example = "did:lem:038dhskjesldkfah")
        private final String tenantInvitationUrl;

        @Schema(name = "Tenant Status", example = "Activate", required = true)
        private Tenant.TenantStatus tenantStatus;

        @Schema(name = "Tenant logo path", example = "/salksdjf/alskdjfkjlj.jpg", required = true)
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
        @Schema(name = "Credential Id", example = "lgcnsudskjd", required = true)
        private final String credentialId;
        @Schema(name = "Credential Name", example = "LG EMP ID", required = true)
        private final String credentialName;
        @Schema(name = "Credential Name", example = "dkjsjebjbvkdjjal", required = true)
        private final String credentialDefinitionId;
        @Schema(name = "Schema Id", example = "schlakdsjfkeknk", required = true)
        private final String schemaId;
        @Schema(name = "Issue Auth URL", example = "http://lil.lgcns.com", required = true)
        private final String authLinkUrl;
        @Schema(name = "Background Image", example = "background.jpg", required = true)
        private final String backgroundImg;
        @Schema(name = "Logo Image", example = "logo.img", required = true)
        private final String logoImg;
    }


}
