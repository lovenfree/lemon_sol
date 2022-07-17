package did.lemonaid.solution.security.filter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import did.lemonaid.solution.domain.account.Account;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class AuthDto {

  @Getter
  @Setter
  @ToString
  public static class LogInRequest {
    @NotNull(message = "필수 파라미터 누락")
    @Schema(description="Id", required = true)
    private String accountId;
    @NotNull(message = "필수 파라미터 누락")
    @Schema(description="패스워드", required = true)
    private String password;
  }


  @Getter
  @Builder
  @ToString
  public static class LogInResponse {
    @Schema(description = "token", example = "")
    private final String token;
  }

}
