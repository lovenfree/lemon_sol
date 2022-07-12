package did.lemonaid.solution.interfaces.trustregistry.lemonaid;

import did.lemonaid.solution.application.faq.FAQFacade;
import did.lemonaid.solution.application.notice.NoticeFacade;
import did.lemonaid.solution.application.term.TermFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@Tag(name="Solution Management", description = "Solution Management API")
@RequiredArgsConstructor
@RequestMapping("/v1/solution-registry")
public class SMController {
  private final NoticeFacade noticeFacade;
  private final TermFacade termFacade;
  private final FAQFacade faqFacade;

  private final SystemManagementMapper mapper;


  @Operation(summary = "Notice Holder List")
  @GetMapping("/notices/holder")
  public ResponseEntity<SystemManagementDto.Notices> retrieveUserNotices() {
    var notices = noticeFacade.retrieveHolderNotices();
    var response = SystemManagementDto.Notices.builder().notices(mapper.of(notices)).build();
    return ResponseEntity.ok(response);
  }


  @Operation(summary = "Notice List")
  @GetMapping("/notices/{tenant-id}")
  public ResponseEntity<SystemManagementDto.Notices> retrieveTenantsNotices(@PathVariable("tenant-id") String tenantId, Optional<SystemManagementDto.NoticeSearchCondition> condition) {
    var notices = noticeFacade.retrieveTenantsNotices(tenantId, condition.orElse(null));
    var response = SystemManagementDto.Notices.builder().notices(mapper.of(notices)).build();
    return ResponseEntity.ok(response);
  }


  @Operation(summary = "FAQ Holder List")
  @GetMapping("/faqs/holder")
  public ResponseEntity<SystemManagementDto.FAQs> retrieveHolderFaqs() {
    var faqs = faqFacade.retrieveHolderFAQs();
    var response = SystemManagementDto.FAQs.builder().faqs(mapper.faqof(faqs)).build();
    return ResponseEntity.ok(response);
  }


  @Operation(summary = "FAQ List")
  @GetMapping("/faqs/{tenant-id}")
  public ResponseEntity<SystemManagementDto.FAQs> retrieveTenantsFAQs(@PathVariable("tenant-id") String tenantId, Optional<SystemManagementDto.FAQSearchCondition> condition) {
    var faqs = faqFacade.retrieveTenantsFAQs(tenantId, condition.orElse(null));
    var response = SystemManagementDto.FAQs.builder().faqs(mapper.faqof(faqs)).build();
    return ResponseEntity.ok(response);
  }

  @Operation(summary = "Term")
  @GetMapping("/term")
  public ResponseEntity<SystemManagementDto.Term> retrieveTerm() {
    var faqs = termFacade.retrieveTerm();
    var response = SystemManagementDto.Term.builder().term(faqs).build();
    return ResponseEntity.ok(response);
  }





}
