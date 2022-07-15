package did.lemonaid.solution.domain.faq;

import did.lemonaid.solution.domain.tenant.Tenant;
import did.lemonaid.solution.domain.tenant.TenantReader;
import did.lemonaid.solution.interfaces.trustregistry.lemonaid.SystemManagementDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class FAQServiceImpl implements FAQService {
  private final FAQReader faqReader;
  private final TenantReader tenantReader;
  private final FAQInfoMapper faqInfoMapper;

  @Override
  public List<FAQInfo.FAQDetail> retrieveHolderFAQs() {
    var faqs = faqReader.retrieveHolderFAQs();
    return faqInfoMapper.of(faqs);
  }

  @Override
  public List<FAQInfo.FAQDetail> retrieveTenantsFAQs(String tenantId, SystemManagementDto.FAQSearchCondition condition) {
    Tenant tenant = tenantReader.getTenant(tenantId);
    List<FAQ> faqs;
    if (tenant.getTenantType().equals(Tenant.TenantType.ISSUER)){
      faqs =  faqReader.retrieveIssuerFAQs(condition);
    }else if (tenant.getTenantType().equals(Tenant.TenantType.VERIFIER)) {
      faqs = faqReader.retrieveVerifierFAQs(condition);
    } else{
      faqs = faqReader.retrieveTenantFAQs(condition);
    }
    return faqInfoMapper.of(faqs);
  }
}
