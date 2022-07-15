package did.lemonaid.solution.application.term;

import did.lemonaid.solution.domain.term.TermService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TermFacade {
  private final TermService termService;

  public String retrieveTerm() {
    return termService.retrieveTerm();
  }
}
