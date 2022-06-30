package did.lemonaid.solution.interfaces.credential;

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
@Tag(name="Credential", description = "Credential Admin API")
@RequiredArgsConstructor
@RequestMapping("/v1/credentials")
public class CredentialController {
  private final CredentialFacade credentialFacade;
  private final CredentialDtoMapper credentialDtoMapper;

  //인증서 목록
  @Operation(summary = "credential List")
  @GetMapping("/tenants/{tenant-id}/credentials")
  public ResponseEntity<CredentialDto.Credentials> retrieveCredentials() {
    var credentials = credentialFacade.retrieveCredentialList();
    var response = TRCredentialDto.Credentials.builder().credentialInfos(credentialDtoMapper.credentialOf(credentials)).build();
    return ResponseEntity.ok(response);
  }


  //인증서 상태 수정 -> 신뢰 인증서 상태 변경\
  @Operation(summary = "update credential")
  @PatchMapping("/credentials/{credential-id}")
  public ResponseEntity<CredentialDto.CredentialResponse> updateCredential (@PathVariable("credential-id") String credentialId, @RequestBody @Valid TRCredentialDto.UpdateCredentialRequest request) {
    var updateCredential = credentialDtoMapper.of(request);
    var response = credentialFacade.updateCredential(credentialId,updateCredential);
    return ResponseEntity.ok(CredentialDto.CredentialResponse.builder().credentialId(response).build());
  }


  //인증서 상세정보
  @Operation(summary = "credential details")
  @GetMapping("/credentials/{credential-definition-id}/")
  public ResponseEntity<CredentialDto.CredentialDetail> retrieveCredential (@PathVariable("credential-definition-id") String credentialDefinitionId) {
    var credential = credentialFacade.retrieveCredential(credentialDefinitionId);
    var response = credentialDtoMapper.of(credential);
    return ResponseEntity.ok(response);
  }


}
