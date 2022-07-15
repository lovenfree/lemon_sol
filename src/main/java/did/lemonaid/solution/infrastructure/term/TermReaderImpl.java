package did.lemonaid.solution.infrastructure.term;

import did.lemonaid.solution.domain.term.TermReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
