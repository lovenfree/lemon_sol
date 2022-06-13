package did.lemonaid.solution.interfaces.account;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import did.lemonaid.solution.domain.account.Account;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class AccountDto {

    @Getter
    @Setter
    @ToString
    public static class AccountInfo {
        @JsonProperty("account_id")
        @NotNull(message = "필수 파라미터 누락")
        @ApiModelProperty(value = "관리자 계정 아이디", example = "admin", required = true)
        String accountId;


        @JsonProperty("account_name")
        @ApiModelProperty(value = "관리자 이름", example = "김라엘", required = true)
        String accountName;

        @JsonProperty("cellular_phone")
        @ApiModelProperty(value = "휴대전화번호", example = "01000000000")
        String cellphoneNumber;

        @JsonProperty("email")
        @ApiModelProperty(value = "이메일", example = "lemonaid@lgcns.com")
        String email;

        @JsonProperty("account_status")
        @ApiModelProperty(value = "계정상태", example = "", required = true)
        Account.AccountStatus accountStatus;

        @JsonProperty("auth_ip")
        @ApiModelProperty(value = "권한IP", example = "0.0.0.0", required = true)
        String authIp;

        @JsonProperty("last_login_time")
        @ApiModelProperty(value = "마지막 로그인 시간")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime lastLoginDateTime;
    }

    @Getter
    @Setter
    public class AccountListResults {
        @ApiModelProperty(value="계정 목록")
        @JsonProperty("accounts")
        List<AccountDto> accounts;
    }

    @Getter
    @Setter
    public class RegisterRequest {
        @JsonProperty("account_id")
        @NotNull(message = "필수 파라미터 누락")
        @ApiModelProperty(value = "관리자 계정 아이디", example = "admin", required = true)
        String accountId;

        @JsonProperty(value = "account_password", access = JsonProperty.Access.WRITE_ONLY)
        @NotNull(message = "필수 파라미터 누락")
        @ApiModelProperty(value="관리자 계정 비밀번호", example="****", required = true)
        String accountPw;

        @JsonProperty("account_name")
        @ApiModelProperty(value = "관리자 이름", example = "김라엘", required = true)
        String accountName;

        @JsonProperty("cellular_phone")
        @ApiModelProperty(value = "휴대전화번호", example = "01000000000")
        String cellphoneNumber;

        @JsonProperty("email")
        @ApiModelProperty(value = "email", example = "lemonaid@lgcns.com")
        String email;

        @JsonProperty("auth_ip")
        @ApiModelProperty(value = "권한IP", example = "0.0.0.0", required = true)
        String authIp;
    }


    @Getter
    @Setter
    @ToString
    public class AccountSearchCondition {
        String accountId;
        String accountName;
        Account.AccountStatus accountStatus;
    }

    @Getter
    @Setter
    @ToString
    public static class RegisterResponse {
        @JsonProperty("account_id")
        @NotNull(message = "필수 파라미터 누락")
        @ApiModelProperty(value = "관리자 계정 아이디", example = "admin", required = true)
        String accountId;

        @JsonProperty("account_name")
        @ApiModelProperty(value = "관리자 이름", example = "김라엘", required = true)
        String accountName;

        @JsonProperty("cellular_phone")
        @ApiModelProperty(value = "휴대전화번호", example = "01000000000")
        String cellphoneNumber;

        @JsonProperty("email")
        @ApiModelProperty(value = "이메일", example = "lemonaid@lgcns.com")
        String email;

//        @JsonProperty("phone_number")
//        @ApiModelProperty(value = "유선전화번호", example = "0200000000")
//        String phoneNumber;

        @JsonProperty("auth_ip")
        @ApiModelProperty(value = "권한IP", example = "0.0.0.0", required = true)
        String authIp;
    }


//    @Getter
//    @Setter
//    @ToString
//    public class AccountInfoResult {
//        @JsonProperty("account_id")
//        @NotNull(message = "필수 파라미터 누락")
//        @ApiModelProperty(value="관리자 계정 아이디", example="admin", required = true)
//        String accountId;
//
////    @JsonProperty(value = "account_password", access = JsonProperty.Access.WRITE_ONLY)
////    @NotNull(message = "필수 파라미터 누락")
////    @ApiModelProperty(value="관리자 계정 비밀번호", example="****", required = true)
////    String accountPw;
//
//        @JsonProperty("account_name")
//        @ApiModelProperty(value="이름", example="김라엘",  required = true)
//        @NotNull(message = "필수 파라미터 누락")
//        String accountName;
//
//        @JsonProperty("rank")
//        @ApiModelProperty(value="직급", example="프로")
//        String rank;
//
//        @JsonProperty("team_name")
//        @ApiModelProperty(value="조직명", example="블록체인기술팀")
//        String teamNm;
//
//
//        @JsonProperty("account_type")
//        @ApiModelProperty(value="관리자 구분", example="부관리자",  required = true)
//        AccountType accountType;
//
//        @JsonProperty("cellular_phone")
//        @ApiModelProperty(value="휴대전화번호", example="01000000000")
//        String cellphoneNumber;
//
//        @JsonProperty("phone_number")
//        @ApiModelProperty(value="유선전화번호", example="0200000000")
//        String phoneNumber;
//
//        @JsonProperty("account_status")
//        @ApiModelProperty(value="계정상태", example="",  required = true)
//        AccountStatus accountStatus;
//
//        @JsonProperty("auth_ip")
//        @ApiModelProperty(value="권한IP", example="0.0.0.0",  required = true)
//        String authIp;
//
//        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
//        @JsonProperty("last_login_time")
//        @ApiModelProperty(value="마지막 로그인 시간", example="0.0.0.0",  required = true)
//        LocalDateTime lastLoginDateTime;
//
//    }




}
