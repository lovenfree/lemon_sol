package did.lemonaid.solution.interfaces.account;

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

public class AccountDto {

  @Getter
  @Setter
  @ToString
  public static class RegisterAccountRequest {
    @NotBlank(message = "필수 파라미터 누락")
    @Schema(description = "관리자 계정 아이디", example = "admin", required = true)
    private String accountId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "필수 파라미터 누락")
    @Schema(description = "관리자 계정 비밀번호", example="****", required = true)
    private String accountPw;
    @NotBlank(message = "필수 파라미터 누락")
    @Schema(description = "관리자 이름", example = "김라엘", required = true)
    private String accountName;
    @NotBlank(message = "필수 파라미터 누락")
    @Schema(description = "휴대전화번호", example = "01000000000", required = true)
    private String mobileNumber;
    @Schema(description = "유선전화번호", example = "01000000000")
    private String phoneNumber;
    @NotBlank(message = "필수 파라미터 누락")
    @Schema(description = "email", example = "lemonaid@lgcns.com", required = true)
    private String email;
    @NotNull(message = "필수 파라미터 누락")
    @Schema(description = "Account Type", example = "ROLE_GENERAL", required = true)
    private Account.AccountType accountType;
    @Schema(description = "권한IP", example = "0.0.0.0")
    private String authIp;
  }


    @Getter
    @Setter
    @ToString
    public static class AccountSearchCondition {
      @Schema(description = "관리자 계정 아이디", example = "admin")
      private String accountId;
      @Schema(description = "관리자 이름", example = "김라엘")
      private String accountName;
      @Schema(description = "Account Status", example = "ACTIVE")
      private  Account.AccountStatus accountStatus;
      @Schema(description = "Account Type", example = "ROLE_GENERAL")
      private  Account.AccountType accountType;
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
  @Builder
  @ToString
  public static class AccountResponse {
    @Schema(description = "관리자 계정 아이디", example = "admin")
    private final String accountId;
  }

  @Getter
  @Builder
  @ToString
  public static class AccountDetail {
    @Schema(description="관리자 계정 아이디", example="admin", required = true)
    private final String accountId;
    @Schema(description="이름", example="김라엘",  required = true)
    private final String accountName;
    @Schema(description="관리자 구분", example="부관리자",  required = true)
    private final Account.AccountType accountType;
    @Schema(description="이메일", example="admin@lgcns.com")
    private final String email;
    @Schema(description="휴대전화번호", example="01000000000")
    private final String mobileNumber;
    @Schema(description="유선전화번호", example="0200000000")
    private final String phoneNumber;
    @Schema(description="계정상태", example="",  required = true)
    private final Account.AccountStatus accountStatus;
    @Schema(description="권한IP", example="0.0.0.0",  required = true)
    private final String authIp;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description="마지막 로그인 시간", example="0.0.0.0",  required = true)
    private final LocalDateTime lastLoginDateTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "revised date", example = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime revisedDate;
  }

  @Getter
  @Builder
  @ToString
  public static class Accounts {
    @Schema(description = "계정 목록")
    private final List<AccountDto.AccountInfo> accounts;
  }

  @Getter
  @Builder
  @ToString
  public static class AccountInfo {
    @Schema(description="관리자 계정 아이디", example="admin")
    private final String accountId;
    @Schema(description="이름", example="김라엘")
    private final String accountName;
    @Schema(description="관리자 구분", example="ROLE_GENERAL")
    private final Account.AccountType accountType;
    @Schema(description="휴대전화번호", example="01000000000")
    private final String mobileNumber;
    @Schema(description="이메일", example="admin@lgcns.com")
    private final String email;
    @Schema(description="계정상태", example="ACTIVE")
    private final Account.AccountStatus accountStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description="마지막 로그인 시간", example="0.0.0.0")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime lastLoginDateTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "revised date", example = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime revisedDate;

  }

  @Getter
  @Setter
  @ToString
  public static class UpdateAccountRequest {
    @NotBlank(message = "필수 파라미터 누락")
    @Schema(description = "관리자 이름", example = "김라엘", required = true)
    private String accountName;
    @NotBlank(message = "필수 파라미터 누락")
    @Schema(description = "휴대전화번호", example = "01000000000", required = true)
    private String mobileNumber;
    @Schema(description = "유선전화번호", example = "01000000000")
    private String phoneNumber;
    @NotBlank(message = "필수 파라미터 누락")
    @Schema(description = "email", example = "lemonaid@lgcns.com", required = true)
    private String email;
    @NotNull(message = "필수 파라미터 누락")
    @Schema(description = "Account Type", example = "ROLE_GENERAL", required = true)
    private Account.AccountType accountType;
    @NotNull(message = "필수 파라미터 누락")
    @Schema(description = "Account Status", example = "ACTIVATE", required = true)
    private Account.AccountStatus accountStatus;
    @Schema(description = "권한IP", example = "0.0.0.0")
    private String authIp;
  }

  @Getter
  @Setter
  @ToString
  public static class UpdateAccountPWRequest {
    @NotBlank(message = "필수 파라미터 누락")
    @Schema(description = "관리자 계정 비밀번호", example="****", required = true)
    private String accountPw;
  }
}
