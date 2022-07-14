package did.lemonaid.solution.interfaces.credential;

import did.lemonaid.solution.application.credential.CredentialFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@RestController
@Tag(name="Credential", description = "Credential Admin API")
@RequiredArgsConstructor
@RequestMapping("/v1/admin/credentials")
public class CredentialController {
  private final CredentialFacade credentialFacade;

  private final CredentialDtoMapper credentialDtoMapper;

  //인증서 목록
  @Operation(summary = "credential List")
  @GetMapping
  public ResponseEntity<CredentialDto.Credentials> retrieveCredentials(Optional<CredentialDto.CredentialSearchCondition> condition) {
    var credentials = credentialFacade.retrieveCredentials(condition.orElse(null));
    var response = CredentialDto.Credentials.builder().credentialInfos(credentialDtoMapper.of(credentials)).build();
    return ResponseEntity.ok(response);
  }

  //인증서 상세정보
  @Operation(summary = "credential details")
  @GetMapping("/{credential-id}")
  public ResponseEntity<CredentialDto.CredentialAdminDetail> retrieveCredential (@PathVariable("credential-id") String credentialId) {
    var credential = credentialFacade.retrieveAdminCredential(credentialId);
    var response = credentialDtoMapper.credentialOf(credential);
    return ResponseEntity.ok(response);
  }

  //인증서 상태 수정 -> 신뢰 인증서 상태 변경\
  @Operation(summary = "update trust credential Status")
  @PatchMapping("/credentialStatus")
  public ResponseEntity<CredentialDto.CredentialResponse> updateCredentialStatus (@RequestBody @Valid CredentialDto.UpdateCredentialStatus request) {
    var credentialStatus = credentialDtoMapper.of(request);
    var response = credentialFacade.changeCredentialStatus(credentialStatus);
    return ResponseEntity.ok(CredentialDto.CredentialResponse.builder().credentialId(response).build());
  }




}
