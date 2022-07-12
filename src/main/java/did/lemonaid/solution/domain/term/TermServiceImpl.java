package did.lemonaid.solution.domain.term;

import did.lemonaid.solution.domain.faq.FAQ;
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
public class TermServiceImpl implements TermService {
  private final TermReader termReader;


  @Override
  public String retrieveTerm() {
    return termReader.retrieveTerm();
  }
}
