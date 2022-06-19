package did.lemonaid.solution.interfaces.trustregistry;

import did.lemonaid.solution.application.tenant.TenantFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@Tag(name="Trust Registry", description = "Trust Registry API")
@RequiredArgsConstructor
@RequestMapping("/api/v1/trust-registry")
public class TRController {
    private final TenantFacade tenantFacade;
//    private final CertifiacateFacade certifiacateFacade;
    private final TrustRegistryDtoMapper mapper;


    @GetMapping("/tenants")
    @Operation(summary = "Tenant List")
    public ResponseEntity<TrustRegistryDto.Tenants> retrieveTenants() {
        var tenants = tenantFacade.retrieveTenants();
        var response = TrustRegistryDto.Tenants.builder().tenants(mapper.of(tenants)).build();
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "certificates List")
    @GetMapping("/tenants/{tenant-id}/credentials")
    public ResponseEntity<TrustRegistryDto.Credentials> retrieveCertificates (@PathVariable("tenant-id") String tenantId) {

        return null;
    }






}
