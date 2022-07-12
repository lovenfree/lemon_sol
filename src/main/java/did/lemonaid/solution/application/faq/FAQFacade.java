package did.lemonaid.solution.application.faq;

import did.lemonaid.solution.domain.faq.FAQInfo;
import did.lemonaid.solution.domain.faq.FAQService;
import did.lemonaid.solution.interfaces.trustregistry.lemonaid.SystemManagementDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class FAQFacade {
  private final FAQService faqService;

  public List<FAQInfo.FAQDetail> retrieveHolderFAQs() {
    return faqService.retrieveHolderFAQs();
  }

  public List<FAQInfo.FAQDetail> retrieveTenantsFAQs(String tenantId, SystemManagementDto.FAQSearchCondition condition) {
    return faqService.retrieveTenantsFAQs(tenantId, condition);
  }
}
