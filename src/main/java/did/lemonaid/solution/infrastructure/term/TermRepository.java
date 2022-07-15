package did.lemonaid.solution.infrastructure.term;

import did.lemonaid.solution.domain.term.Term;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TermRepository extends JpaRepository<Term, Long> {
  Optional<String> retrieveTerm();

}
