package did.lemonaid.solution.infrastructure.term;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static did.lemonaid.solution.domain.term.QTerm.term;

@Repository
@RequiredArgsConstructor
public class TermRepositoryImpl {

  private final JPAQueryFactory queryFactory;


  public Optional<String> retrieveTerm(){
    return Optional.ofNullable(queryFactory.select(Projections.constructor(String.class, term.termContent))
      .from(term)
      .orderBy(term.revisedDate.desc())
      .fetchOne());
  }


}
