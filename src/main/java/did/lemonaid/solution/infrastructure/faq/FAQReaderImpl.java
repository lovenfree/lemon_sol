package did.lemonaid.solution.infrastructure.faq;

import did.lemonaid.solution.domain.faq.FAQ;
import did.lemonaid.solution.domain.faq.FAQReader;
import did.lemonaid.solution.interfaces.trustregistry.lemonaid.SystemManagementDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class FAQReaderImpl implements FAQReader {
  private final FAQRepository faqRepository;
  @Override
  public List<FAQ> retrieveHolderFAQs() {
    return faqRepository.retrieveHolderFAQs();
  }

  @Override
  public List<FAQ> retrieveTenantFAQs(SystemManagementDto.FAQSearchCondition condition) {
    return faqRepository.retrieveTenantFAQs(condition);
  }

  @Override
  public List<FAQ> retrieveIssuerFAQs(SystemManagementDto.FAQSearchCondition condition) {
    return faqRepository.retrieveIssuerFAQs(condition);
  }

  @Override
  public List<FAQ> retrieveVerifierFAQs(SystemManagementDto.FAQSearchCondition condition) {
    return faqRepository.retrieveVerifierFAQs(condition);
  }

}
