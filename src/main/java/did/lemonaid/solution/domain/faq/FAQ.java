package did.lemonaid.solution.domain.faq;

import did.lemonaid.solution.domain.BaseEntity;
import did.lemonaid.solution.domain.notice.Notice;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name="FAQ")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FAQ extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="ID")
  private Long id;

  @Column(name="FAQ_CLASS", nullable = false)
  @Enumerated(EnumType.STRING)
  private FAQClass faqClass;

  @Column(name="FAQ_TITLE", nullable = false)
  private String faqTitle;

  @Column(name="FAQ_DISPLAY_RANGE", nullable = false)
  @Enumerated(EnumType.STRING)
  private DisplayRange displayRange;

  @Column(name="FAQ_CONTENT", nullable = false)
  private String faqContent;

  @Column(name="NOTICE_STATUS", nullable = false)
  @Enumerated(EnumType.STRING)
  private FAQStatus faqStatus;


  @Getter
  @AllArgsConstructor
  public enum FAQClass{
    TENANT("참여기관"), PRICING("과금정책"), SYSTEM("시스템장애"), ETC("기타");
    private final String description;
  }

  @Getter
  @AllArgsConstructor
  public enum DisplayRange{
    ISSUER("Issuer"), VERIFIER("Verifier"), HOLDER("Holder"), ALL("All");
    private final String description;
  }

  @Getter
  @AllArgsConstructor
  public enum FAQStatus{
    WRITING("작성중"), PUBLISH("발행"), EXPIRATION("만료");
    private final String description;
  }
}




