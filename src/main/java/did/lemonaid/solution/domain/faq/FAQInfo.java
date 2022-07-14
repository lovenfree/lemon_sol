package did.lemonaid.solution.domain.faq;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

public class FAQInfo {


  @Getter
  @Builder
  @ToString
  public static class FAQDetail {
    private final Long id;
    private final FAQ.FAQClass faqClass;
    private final String faqTitle;
    private final String faqContent;
    private final LocalDateTime revisedDate;
  }
}
