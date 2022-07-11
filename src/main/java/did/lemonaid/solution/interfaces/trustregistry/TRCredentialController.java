package did.lemonaid.solution.interfaces.trustregistry;

import did.lemonaid.solution.application.credential.CredentialFacade;
import did.lemonaid.solution.interfaces.trustregistry.credential.TRCredentialDto;
import did.lemonaid.solution.interfaces.trustregistry.credential.TRCredentialDtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@Tag(name="Credential", description = "Trust Registry Credential API")
@RequiredArgsConstructor
@RequestMapping("/v1/trust-registry")
public class TRCredentialController {
  private final CredentialFacade credentialFacade;
  private final TRCredentialDtoMapper credentialDtoMapper;



  //인증서 등록
  @Operation(summary = "register credential")
  @PostMapping("/credentials")
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
