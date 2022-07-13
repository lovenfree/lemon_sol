package did.lemonaid.solution.infrastructure.term;

import did.lemonaid.solution.domain.notice.Notice;
import did.lemonaid.solution.domain.notice.NoticeReader;
import did.lemonaid.solution.domain.term.Term;
import did.lemonaid.solution.domain.term.TermReader;
import did.lemonaid.solution.interfaces.trustregistry.lemonaid.SystemManagementDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class TermReaderImpl implements TermReader {
  private final TermRepository termRepository;

  @Override
  public String retrieveTerm() {
    return termRepository.retrieveTerm().orElse("");
  }
}
