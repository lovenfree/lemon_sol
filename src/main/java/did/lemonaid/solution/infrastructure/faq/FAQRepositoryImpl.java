package did.lemonaid.solution.infrastructure.faq;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import did.lemonaid.solution.domain.faq.FAQ;
import did.lemonaid.solution.interfaces.trustregistry.lemonaid.SystemManagementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static did.lemonaid.solution.domain.faq.QFAQ.fAQ;
import static org.springframework.util.StringUtils.hasLength;

@Repository
@RequiredArgsConstructor
public class FAQRepositoryImpl {

  private final JPAQueryFactory queryFactory;

  public List<FAQ> retrieveHolderFAQs(){
    return queryFactory.select(fAQ)
      .from(fAQ)
      .where(faqRangeEq(FAQ.DisplayRange.HOLDER).or(faqRangeEq(FAQ.DisplayRange.ALL)),
        faqStatusEq(FAQ.FAQStatus.PUBLISH))
      .orderBy(fAQ.revisedDate.desc())
      .fetch();
  }

  public List<FAQ> retrieveIssuerFAQs(SystemManagementDto.FAQSearchCondition condition){
    return queryFactory.select(fAQ)
      .from(fAQ)
      .where(faqRangeEq(FAQ.DisplayRange.ISSUER).or(faqRangeEq(FAQ.DisplayRange.ALL)),
        faqStatusEq(FAQ.FAQStatus.PUBLISH),
        faqTitleContains(condition.getFaqTitle()))
      .orderBy(fAQ.revisedDate.desc())
      .fetch();
  }

  public List<FAQ> retrieveVerifierFAQs(SystemManagementDto.FAQSearchCondition condition){
    return queryFactory.select(fAQ)
      .from(fAQ)
      .where(faqRangeEq(FAQ.DisplayRange.VERIFIER).or(faqRangeEq(FAQ.DisplayRange.ALL)),
        faqStatusEq(FAQ.FAQStatus.PUBLISH),
        faqTitleContains(condition.getFaqTitle()))
      .orderBy(fAQ.revisedDate.desc())
      .fetch();
  }

  public List<FAQ> retrieveTenantFAQs(SystemManagementDto.FAQSearchCondition condition){
    return queryFactory.select(fAQ)
      .from(fAQ)
      .where(faqRangeEq(FAQ.DisplayRange.ISSUER).or(faqRangeEq(FAQ.DisplayRange.VERIFIER)).and(faqRangeEq(FAQ.DisplayRange.ALL)),
       faqStatusEq(FAQ.FAQStatus.PUBLISH),
       faqTitleContains(condition.getFaqTitle()))
      .orderBy(fAQ.revisedDate.desc())
      .fetch();
  }

  private BooleanExpression faqTitleContains(String title) {
    return !hasLength(title) ? null : fAQ.faqTitle.contains(title);
  }

  private BooleanExpression faqRangeEq(FAQ.DisplayRange faqRange) {
    return faqRange ==null ? null : fAQ.displayRange.eq(faqRange);
  }


  private BooleanExpression faqStatusEq(FAQ.FAQStatus faqStatus) {
    return faqStatus ==null ? null : fAQ.faqStatus.eq(faqStatus);
  }

//  private BooleanExpression dateBetween(LocalDateTime searchStartDate, LocalDateTime searchEndDate) {
//    if(searchStartDate == null || searchEndDate ==null){
//      return null;
//    }
//    return credential.revisedDate.between(searchStartDate, searchEndDate);
//  }

}
