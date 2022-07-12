package did.lemonaid.solution.infrastructure.term;

import did.lemonaid.solution.domain.notice.Notice;
import did.lemonaid.solution.domain.term.Term;
import did.lemonaid.solution.interfaces.trustregistry.lemonaid.SystemManagementDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TermRepository extends JpaRepository<Term, Long> {
  Optional<String> retrieveTerm();

}
