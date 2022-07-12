package did.lemonaid.solution.domain.faq;


import did.lemonaid.solution.interfaces.trustregistry.lemonaid.SystemManagementDto;

import java.util.List;

public interface FAQReader {
  List<FAQ> retrieveHolderFAQs();

  List<FAQ> retrieveTenantFAQs(SystemManagementDto.FAQSearchCondition condition);
  List<FAQ> retrieveIssuerFAQs(SystemManagementDto.FAQSearchCondition condition);
  List<FAQ> retrieveVerifierFAQs(SystemManagementDto.FAQSearchCondition condition);
}
