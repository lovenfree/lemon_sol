package did.lemonaid.solution.interfaces.trustregistry;

import did.lemonaid.solution.application.credential.CredentialFacade;
import did.lemonaid.solution.application.tenant.TenantFacade;
import did.lemonaid.solution.interfaces.tenant.TenantDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Slf4j
@RestController
@Tag(name="Trust Registry-Public", description = "Trust Registry API")
@RequiredArgsConstructor
@RequestMapping("/api/v1/trust-registry")
public class TRController {
    private final TenantFacade tenantFacade;
    private final CredentialFacade credentialFacade;
    private final TrustRegistryDtoMapper mapper;


    @Operation(summary = "Tenant List")
    @GetMapping("/tenants")
    public ResponseEntity<TrustRegistryDto.Tenants> retrieveTenants() {
        var tenants = tenantFacade.retrieveTenants();
        var response = TrustRegistryDto.Tenants.builder().tenants(mapper.of(tenants)).build();
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "credential List")
    @GetMapping("/tenants/{tenant-id}/credentials")
    public ResponseEntity<TrustRegistryDto.Credentials> retrieveCertificates (@PathVariable("tenant-id") String tenantId) {

        return null;
    }


    //인증서 등록
    @Operation(summary = "register credential")
    @PostMapping ("/credentials")
    public ResponseEntity<TrustRegistryDto.CredentialResponse> registerCredential (@RequestBody @Valid TrustRegistryDto.RegisterCredentialRequest request) {

      return ResponseEntity.ok(null);
    }


    //인증서 상세정보
    @Operation(summary = "credential details")
    @GetMapping("/credentials/{credential-definition-id}/")
    public ResponseEntity<TrustRegistryDto.CredentialDetail> retrieveCertificate (@PathVariable("credential-definition-id") String credentialDefinitionId) {

      return ResponseEntity.ok(null);
    }




}
