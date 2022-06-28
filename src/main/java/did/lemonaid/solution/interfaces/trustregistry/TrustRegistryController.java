package did.lemonaid.solution.interfaces.trustregistry;

import did.lemonaid.solution.application.credential.CredentialFacade;
import did.lemonaid.solution.application.tenant.TenantFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Slf4j
@RestController
@Tag(name="Trust Registry-Public", description = "Trust Registry API")
@RequiredArgsConstructor
@RequestMapping("/v1/trust-registry")
public class TrustRegistryController {
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

  @PostMapping("/tenants/{tenant-id}/activate")
  @Operation(summary="Activate Tenant")
  public ResponseEntity<TrustRegistryDto.TenantResponse> activateTenant(@Valid @PathVariable("tenant-id") String tenantId, @RequestBody @Valid TrustRegistryDto.ActivateTenantRequest request) {
    var tenantCommand = mapper.activateOf(request);
    var result = tenantFacade.activateTenant(tenantId, tenantCommand);
    var response = mapper.of(result);
    return ResponseEntity.ok(response);
  }

    @Operation(summary = "credential List")
    @GetMapping("/tenants/{tenant-id}/credentials")
    public ResponseEntity<TrustRegistryDto.Credentials> retrieveCertificates (@PathVariable("tenant-id") String tenantId) {
      var credentials = tenantFacade.retrieveCredentialList(tenantId);
      var response = TrustRegistryDto.Credentials.builder().credentialInfos(mapper.credentialOf(credentials)).build();
      return ResponseEntity.ok(response);
    }


    //인증서 등록
    @Operation(summary = "register credential")
    @PostMapping ("/credentials")
    public ResponseEntity<TrustRegistryDto.CredentialResponse> registerCredential (@RequestBody @Valid TrustRegistryDto.RegisterCredentialRequest request) {
      var registerCredential = mapper.of(request);
      var tenantID = request.getTenantId();
      var response = credentialFacade.registerCredential(registerCredential,tenantID);
      return ResponseEntity.ok(TrustRegistryDto.CredentialResponse.builder().credentialId(response).build());
    }


    //인증서 수정
    @Operation(summary = "update credential")
    @PatchMapping("/credentials/{credential-id}")
    public ResponseEntity<TrustRegistryDto.CredentialResponse> updateCredential (@PathVariable("credential-id") String credentialId, @RequestBody @Valid TrustRegistryDto.UpdateCredentialRequest request) {
      var updateCredential = mapper.of(request);
      var response = credentialFacade.updateCredential(credentialId,updateCredential);
      return ResponseEntity.ok(TrustRegistryDto.CredentialResponse.builder().credentialId(response).build());
    }


    //인증서 상세정보
    @Operation(summary = "credential details")
    @GetMapping("/credentials/{credential-definition-id}/")
    public ResponseEntity<TrustRegistryDto.CredentialDetail> retrieveCertificate (@PathVariable("credential-definition-id") String credentialDefinitionId) {
      var credential = credentialFacade.retrieveCredential(credentialDefinitionId);
      var response = mapper.of(credential);
      return ResponseEntity.ok(response);
    }

}
