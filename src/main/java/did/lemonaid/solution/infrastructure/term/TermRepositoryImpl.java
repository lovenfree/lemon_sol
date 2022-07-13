package did.lemonaid.solution.infrastructure.term;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import did.lemonaid.solution.domain.credential.CredentialInfo;
import did.lemonaid.solution.domain.term.Term;
import did.lemonaid.solution.interfaces.trustregistry.lemonaid.SystemManagementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static did.lemonaid.solution.domain.term.QTerm.term;
import static org.springframework.util.StringUtils.hasLength;

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
