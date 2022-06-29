package did.lemonaid.solution.interfaces.credential;

import did.lemonaid.solution.application.credential.CredentialFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Tag(name="Credential", description = "Credential Admin API")
@RequiredArgsConstructor
@RequestMapping("/v1/credentials")
public class CredentialController {
  private final CredentialFacade credentialFacade;



}
