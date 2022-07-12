package did.lemonaid.solution.interfaces.trustregistry.lemonaid;

import did.lemonaid.solution.application.credential.CredentialFacade;
import did.lemonaid.solution.application.faq.FAQFacade;
import did.lemonaid.solution.application.notice.NoticeFacade;
import did.lemonaid.solution.application.term.TermFacade;
import did.lemonaid.solution.interfaces.trustregistry.credential.TRCredentialDtoMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Tag(name="Solution Management", description = "Solution Management API")
@RequiredArgsConstructor
@RequestMapping("/v1/solution-registry")
public class SMController {
  private final NoticeFacade noticeFacade;
  private final TermFacade termFacade;
  private final FAQFacade faqFacade;





}
