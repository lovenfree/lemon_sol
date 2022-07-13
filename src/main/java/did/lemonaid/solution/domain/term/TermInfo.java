package did.lemonaid.solution.domain.term;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

public class TermInfo {


  @Getter
  @Builder
  @ToString
  public static class TermDetail {
    private final String termContent;
  }
}
