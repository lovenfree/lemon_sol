package did.lemonaid.solution.domain.notice;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

public class NoticeInfo {


  @Getter
  @Builder
  @ToString
  public static class NoticeDetail {
    private final Long id;
    private final Notice.NoticeLevel noticeLevel;
    private final String noticeTitle;
    private final String noticeContent;
    private final LocalDateTime revisedDate;
  }
}
