package did.lemonaid.solution.domain.notice;

import did.lemonaid.solution.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name="NOTICE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="ID")
  private Long id;

  @Column(name="NOTICE_LEVEL", nullable = false)
  @Enumerated(EnumType.STRING)
  private NoticeLevel noticeLevel;

  @Column(name="NOTICE_TITLE", nullable = false)
  private String noticeTitle;

  @Column(name="NOTICE_RANGE", nullable = false)
  @Enumerated(EnumType.STRING)
  private NoticeRange noticeRange;

  @Column(name="NOTICE_CONTENT", nullable = false)
  private String noticeContent;

  @Column(name="NOTICE_STATUS", nullable = false)
  @Enumerated(EnumType.STRING)
  private NoticeStatus noticeStatus;

  @Column(name="NOTICE_START_DATE")
  private LocalDateTime noticeStartDate;

  @Column(name="NOTICE_END_DATE")
  private LocalDateTime noticeEndDate;

  @Getter
  @AllArgsConstructor
  public enum NoticeLevel{
    CRITICAL("중요공지"), GENERAL("일반공지");
    private final String description;
  }

  @Getter
  @AllArgsConstructor
  public enum NoticeStatus{
    WRITING("작성중"), PUBLISH("발행"), EXPIRATION("만료");
    private final String description;
  }

  @Getter
  @AllArgsConstructor
  public enum NoticeRange{
    ISSUER("Issuer"), VERIFIER("Verifier"), HOLDER("Holder"), ALL("All");
    private final String description;
  }

}







