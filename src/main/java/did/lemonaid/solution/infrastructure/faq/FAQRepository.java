package did.lemonaid.solution.infrastructure.faq;

import did.lemonaid.solution.domain.faq.FAQ;
import did.lemonaid.solution.interfaces.trustregistry.lemonaid.SystemManagementDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FAQRepository extends JpaRepository<FAQ, Long> {
  List<FAQ> retrieveHolderFAQs();
  List<FAQ> retrieveTenantFAQs(SystemManagementDto.FAQSearchCondition condition);
  List<FAQ> retrieveIssuerFAQs(SystemManagementDto.FAQSearchCondition condition);
  List<FAQ> retrieveVerifierFAQs(SystemManagementDto.FAQSearchCondition condition);
}
