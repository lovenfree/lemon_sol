package did.lemonaid.solution.domain.term;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
