package did.lemonaid.solution.domain.account;


import did.lemonaid.solution.common.util.Util;
import did.lemonaid.solution.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name="ACCOUNT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @Column(name="ACCOUNT_ID", nullable = false)
    private String accountId;

    @Column(name="PSWD_HASH_VAL")
    private String accountPwHash;

    @Column(name="PSWD_FALR_TMCNT", nullable = false)
    private int  accountPwFailCount;

    @Column(name="FLNM", nullable = false)
    private String accountName;

    @Column(name="CPNO")
    private String mobileNumber;

    @Column(name="EMAIL")
    private String email;

    @Column(name="ACCOUNT_STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

  @Column(name="ACCOUNT_TYPE", nullable = false)
  @Enumerated(EnumType.STRING)
  private AccountType accountType;
  @Column(name="AUTH_IP")
    private String authIp;

    @Column(name="LAST_LOGIN_DTTM")
    private LocalDateTime lastLoginDateTime;

    @Column(name="LAST_PSWD_CHNG_DTTM")
    private LocalDateTime lastPwChangeDateTime;

    @Column(name="LOGIN_SESN_ID")
    private String loginSessionId;

    @Getter
    @AllArgsConstructor
    public enum AccountStatus{
        ACTIVATE("활성화"), DEACTIVATE("비활성화");
        private final String description;
    }

  @Getter
  @AllArgsConstructor
  public enum AccountType{
    ADMIN("주관리자"), GENERAL("부관리자");
    private final String description;
  }


    @Builder
    public Account(String accountId, String accountPw, String accountName, String mobileNumber,
                   String email, AccountStatus accountStatus, AccountType accountType, String authIp) {
        this.accountId = accountId;
        this.accountPwHash = accountPw;
        this.accountName = accountName;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.accountStatus = accountStatus;
        this.accountType = accountType;
        this.authIp = authIp;

        // NOT NULL 초기화
        this.accountPwFailCount = 0;

        setProgramIdInfo(Util.getMethodName());

    }


    public void updateAccountInfo(Account account) {

        this.accountName = account.getAccountName();
        this.mobileNumber = account.getMobileNumber();
        this.email = account.getEmail();
        this.accountStatus = account.getAccountStatus();
        this.authIp = account.getAuthIp();
        updateProgramIdInfo(Util.getMethodName());
    }

    public void updatePwInfo(String pwHash) {
        this.accountPwHash = pwHash;
        this.lastPwChangeDateTime = LocalDateTime.now();
        updateProgramIdInfo(Util.getMethodName());
    }

    public void updateLogInInfo() {
        this.accountPwFailCount = 0;
        this.lastLoginDateTime = LocalDateTime.now();

    }

    public void updateFailInfo() {
        this.accountPwFailCount = this.accountPwFailCount+=1;
    }

}
