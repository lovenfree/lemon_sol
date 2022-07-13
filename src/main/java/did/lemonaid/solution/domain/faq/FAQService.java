package did.lemonaid.solution.domain.faq;

import did.lemonaid.solution.interfaces.trustregistry.lemonaid.SystemManagementDto;

import java.util.List;

public interface FAQService {
  List<FAQInfo.FAQDetail> retrieveHolderFAQs();

  List<FAQInfo.FAQDetail> retrieveTenantsFAQs(String tenantId, SystemManagementDto.FAQSearchCondition condition);
}
