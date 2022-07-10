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
@Tag(name="Trust Registry-Public", description = "Trust Registry API")
@RequiredArgsConstructor
@RequestMapping("/v1/trust-registry")
public class TrustRegistryController {
    private final TenantFacade tenantFacade;
    private final CredentialFacade credentialFacade;
    private final TRCredentialDtoMapper credentialDtoMapper;
    private final TRTenantDtoMapper tenantDtoMapper;


    @Operation(summary = "Tenant List")
    @GetMapping("/tenants")
    public ResponseEntity<TRTenantDto.Tenants> retrieveActiveTenants() {
        var tenants = tenantFacade.retrieveActiveTenants();
        var response = TRTenantDto.Tenants.builder().tenants(tenantDtoMapper.of(tenants)).build();
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

  @PostMapping("/tenants/{tenant-id}/activate")
  @Operation(summary="Activate Tenant")
  public ResponseEntity<TRTenantDto.TenantResponse> activateTenant(@Valid @PathVariable("tenant-id") String tenantId, @RequestBody @Valid TRTenantDto.ActivateTenantRequest request) {
    var tenantCommand = tenantDtoMapper.activateOf(request);
    var result = tenantFacade.activateTenant(tenantId, tenantCommand);
    var response = tenantDtoMapper.of(result);
    return ResponseEntity.ok(response);
  }

    @Operation(summary = "credential List")
    @GetMapping("/tenants/{tenant-id}/credentials")
    public ResponseEntity<TRCredentialDto.Credentials> retrieveCredentials(@PathVariable("tenant-id") String tenantId) {
      var credentials = tenantFacade.retrieveCredentialList(tenantId);
      var response = TRCredentialDto.Credentials.builder().credentialInfos(credentialDtoMapper.credentialOf(credentials)).build();
      return ResponseEntity.ok(response);
    }


    //인증서 등록
    @Operation(summary = "register credential")
    @PostMapping ("/credentials")
    public ResponseEntity<TRCredentialDto.CredentialResponse> registerCredential (@RequestBody @Valid TRCredentialDto.RegisterCredentialRequest request) {
      var registerCredential = credentialDtoMapper.of(request);
      var tenantID = request.getTenantId();
      var response = credentialFacade.registerCredential(registerCredential,tenantID);
      return ResponseEntity.ok(TRCredentialDto.CredentialResponse.builder().credentialId(response).build());
    }


    //인증서 수정
    @Operation(summary = "update credential")
    @PatchMapping("/credentials/{credential-definition-id}")
    public ResponseEntity<TRCredentialDto.CredentialResponse> updateCredential (@PathVariable("credential-definition-id") String credentialDefinitionId, @RequestBody @Valid TRCredentialDto.UpdateCredentialRequest request) {
      var updateCredential = credentialDtoMapper.of(request);
      var response = credentialFacade.updateCredential(credentialDefinitionId,updateCredential);
      return ResponseEntity.ok(TRCredentialDto.CredentialResponse.builder().credentialId(response).build());
    }


    //인증서 상세정보
    @Operation(summary = "credential details")
    @GetMapping("/credentials/{credential-definition-id}/")
    public ResponseEntity<TRCredentialDto.CredentialDetail> retrieveCredential (@PathVariable("credential-definition-id") String credentialDefinitionId) {
      var credential = credentialFacade.retrieveCredential(credentialDefinitionId);
      var response = credentialDtoMapper.of(credential);
      return ResponseEntity.ok(response);
    }

}
