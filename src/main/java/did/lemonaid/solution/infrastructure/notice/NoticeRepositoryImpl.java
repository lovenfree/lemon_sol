package did.lemonaid.solution.infrastructure.notice;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import did.lemonaid.solution.domain.notice.Notice;
import did.lemonaid.solution.interfaces.trustregistry.lemonaid.SystemManagementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static did.lemonaid.solution.domain.credential.QCredential.credential;
import static did.lemonaid.solution.domain.notice.QNotice.notice;
import static org.springframework.util.StringUtils.hasLength;

@Repository
@RequiredArgsConstructor
public class NoticeRepositoryImpl {

  private final JPAQueryFactory queryFactory;

  public List<Notice> retrieveHolderNotices(){
    return queryFactory.select(notice)
      .from(notice)
      .where(noticeRangeEq(Notice.NoticeRange.HOLDER).or(noticeRangeEq(Notice.NoticeRange.ALL)),
        noticeStatusEq(Notice.NoticeStatus.PUBLISH))
      .orderBy(notice.revisedDate.desc())
      .fetch();
  }

  public List<Notice> retrieveIssuerNotices(SystemManagementDto.NoticeSearchCondition condition){
    return queryFactory.select(notice)
      .from(notice)
      .where(noticeRangeEq(Notice.NoticeRange.ISSUER).or(noticeRangeEq(Notice.NoticeRange.ALL)),
        noticeStatusEq(Notice.NoticeStatus.PUBLISH),
        noticeTitleContains(condition.getNoticeTitle()), dateBetween(condition.getSearchStartDate(), condition.getSearchEndDate()))
      .orderBy(notice.revisedDate.desc())
      .fetch();
  }

  public List<Notice> retrieveVerifierNotices(SystemManagementDto.NoticeSearchCondition condition){
    return queryFactory.select(notice)
      .from(notice)
      .where(noticeRangeEq(Notice.NoticeRange.VERIFIER).or(noticeRangeEq(Notice.NoticeRange.ALL)),
        noticeStatusEq(Notice.NoticeStatus.PUBLISH),
        noticeTitleContains(condition.getNoticeTitle()), dateBetween(condition.getSearchStartDate(), condition.getSearchEndDate()))
      .orderBy(notice.revisedDate.desc())
      .fetch();
  }

  public List<Notice> retrieveTenantNotices(SystemManagementDto.NoticeSearchCondition condition){
    return queryFactory.select(notice)
      .from(notice)
      .where(noticeRangeEq(Notice.NoticeRange.ISSUER).or(noticeRangeEq(Notice.NoticeRange.VERIFIER)).or(noticeRangeEq(Notice.NoticeRange.ALL)),
       noticeStatusEq(Notice.NoticeStatus.PUBLISH),
       noticeTitleContains(condition.getNoticeTitle()), dateBetween(condition.getSearchStartDate(), condition.getSearchEndDate()))
      .orderBy(notice.revisedDate.desc())
      .fetch();
  }

  private BooleanExpression noticeTitleContains(String title) {
    return !hasLength(title) ? null : notice.noticeTitle.contains(title);
  }

  private BooleanExpression noticeRangeEq(Notice.NoticeRange noticeRange) {
    return noticeRange ==null ? null : notice.noticeRange.eq(noticeRange);
  }


  private BooleanExpression noticeStatusEq(Notice.NoticeStatus noticeStatus) {
    return noticeStatus ==null ? null : notice.noticeStatus.eq(noticeStatus);
  }

  private BooleanExpression dateBetween(LocalDateTime searchStartDate, LocalDateTime searchEndDate) {
    if(searchStartDate == null || searchEndDate ==null){
      return null;
    }
    return credential.revisedDate.between(searchStartDate, searchEndDate);
  }

}
