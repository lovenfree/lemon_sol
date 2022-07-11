package did.lemonaid.solution.interfaces.trustregistry;

import did.lemonaid.solution.application.credential.CredentialFacade;
import did.lemonaid.solution.application.tenant.TenantFacade;
import did.lemonaid.solution.interfaces.trustregistry.credential.TRCredentialDto;
import did.lemonaid.solution.interfaces.trustregistry.credential.TRCredentialDtoMapper;
import did.lemonaid.solution.interfaces.trustregistry.tenant.TRTenantDto;
import did.lemonaid.solution.interfaces.trustregistry.tenant.TRTenantDtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Slf4j
@RestController
@Tag(name="Tenant", description = "Trust Registry Tenant API")
@RequiredArgsConstructor
@RequestMapping("/v1/trust-registry/tenants")
public class TRTenantController {
    private final TenantFacade tenantFacade;
    private final TRTenantDtoMapper tenantDtoMapper;
    private final TRCredentialDtoMapper credentialDtoMapper;


    @Operation(summary = "Tenant List")
    @GetMapping
    public ResponseEntity<TRTenantDto.Tenants> retrieveActiveTenants() {
        var tenants = tenantFacade.retrieveActiveTenants();
        var response = TRTenantDto.Tenants.builder().tenants(tenantDtoMapper.of(tenants)).build();
        return ResponseEntity.ok(response);
    }

  @Operation(summary = "credential List")
  @GetMapping("/{tenant-id}/credentials")
  public ResponseEntity<TRCredentialDto.Credentials> retrieveCredentials(@PathVariable("tenant-id") String tenantId) {
    var credentials = tenantFacade.retrieveCredentialList(tenantId);
    var response = TRCredentialDto.Credentials.builder().credentialInfos(credentialDtoMapper.credentialOf(credentials)).build();
    return ResponseEntity.ok(response);
  }


  @Operation(summary = "Issuer List")
  @GetMapping("/issuers")
  public ResponseEntity<TRTenantDto.Tenants> retrieveActiveIssuers() {
    var tenants = tenantFacade.retrieveActiveIssuers();
    var response = TRTenantDto.Tenants.builder().tenants(tenantDtoMapper.of(tenants)).build();
    return ResponseEntity.ok(response);
  }

  @Operation(summary = "Verifier List")
  @GetMapping("/verifiers")
  public ResponseEntity<TRTenantDto.Tenants> retrieveActiveVerifiers() {
    var tenants = tenantFacade.retrieveActiveVerifiers();
    var response = TRTenantDto.Tenants.builder().tenants(tenantDtoMapper.of(tenants)).build();
    return ResponseEntity.ok(response);
  }

  @PostMapping("/{tenant-id}/activate")
  @Operation(summary="Activate Tenant")
  public ResponseEntity<TRTenantDto.TenantResponse> activateTenant(@Valid @PathVariable("tenant-id") String tenantId, @RequestBody @Valid TRTenantDto.ActivateTenantRequest request) {
    var tenantCommand = tenantDtoMapper.activateOf(request);
    var result = tenantFacade.activateTenant(tenantId, tenantCommand);
    var response = tenantDtoMapper.of(result);
    return ResponseEntity.ok(response);
  }

}
