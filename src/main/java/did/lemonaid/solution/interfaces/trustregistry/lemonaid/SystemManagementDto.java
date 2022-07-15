package did.lemonaid.solution.interfaces.trustregistry.lemonaid;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import did.lemonaid.solution.domain.faq.FAQ;
import did.lemonaid.solution.domain.notice.Notice;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

public class SystemManagementDto {
  @Getter
  @Builder
  @ToString
  public static class Notices {
    @JsonProperty("notices")
    private final List<SystemManagementDto.NoticeInfo> notices;
  }

  @Getter
  @Builder
  @ToString
  public static class NoticeInfo {
    private final Long id;
    private final Notice.NoticeLevel noticeLevel;
    private final String noticeTitle;
    private final String noticeContent;
    private final LocalDateTime revisedDate;
  }

  @Getter
  @Setter
  @Builder
  @ToString
  public static class NoticeSearchCondition{
    @Schema(description = "Tenant Title", example = "BOTH")
    private final String noticeTitle;
    @Schema(description="search start date", example="2021-01-01 00:00:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime searchStartDate;
    @Schema(description="search end date", example="2022-12-31 23:59:59")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime searchEndDate;
  }

  @Getter
  @Setter
  @Builder
  @ToString
  public static class FAQSearchCondition {
    @Schema(description = "FAQ Title", example = "BOTH")
    private final String faqTitle;
    @Schema(description = "FAQ Class", example = "PRICING")
    private final FAQ.FAQClass faqClass;
  }

  @Getter
  @Builder
  @ToString
  public static class FAQs {
    @JsonProperty("faqs")
    private final List<SystemManagementDto.FAQInfo> faqs;
  }

  @Getter
  @Builder
  @ToString
  public static class FAQInfo {
    private final Long id;
    private final FAQ.FAQClass faqClass;
    private final String faqTitle;
    private final String faqContent;
    private final LocalDateTime revisedDate;
  }

  @Getter
  @Builder
  @ToString
  public static class Term {
    private final String term;
  }
}
