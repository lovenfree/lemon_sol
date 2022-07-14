package did.lemonaid.solution.interfaces.account;

import did.lemonaid.solution.application.account.AccountFacade;
import did.lemonaid.solution.interfaces.BooleanResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@Tag(name="Account", description = "Account admin API")
@RequiredArgsConstructor
@RequestMapping("/v1/admin/accounts")
@Slf4j
public class AccountController {

    private final AccountFacade accountFacade;
    private final AccountDtoMapper accountDtoMapper;

    @Operation(summary = "관리자 계정 생성")
    @PostMapping
    public ResponseEntity<AccountDto.AccountResponse> createAccount(@RequestBody @Valid AccountDto.RegisterAccountRequest request) {
        var command =  accountDtoMapper.of(request);
        var accountId = accountFacade.registerAccount(command);
        var response = accountDtoMapper.of(accountId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary="관리자 목록 조회")
    @GetMapping
//  @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ASSISTANT','ROLE_GENERAL')")
    public ResponseEntity<List<AccountDto.AccountInfo>> retrieveAccounts(Optional<AccountDto.AccountSearchCondition> condition){
      var accounts = accountFacade.retrieveAccounts(condition.orElse(null));
      var response = accountDtoMapper.of(accounts);
      return ResponseEntity.ok(response);
    }

    @Operation(summary="관리자 계정 조회")
    @GetMapping("/{account_id:.+}")
  //  @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ASSISTANT','ROLE_GENERAL')")
    public ResponseEntity<AccountDto.AccountDetail> retrieveAccount(@Valid @PathVariable("account_id") String accountId) {
      var account = accountFacade.retrieveAccount(accountId);
      var response = accountDtoMapper.of(account);
      return ResponseEntity.ok(response);
    }

    @Operation(summary="아이디 중복 여부 체크")
    @GetMapping("/{account_id:.+}/valid")
  //  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<BooleanResult> checkDuplicateAccount(@Valid @PathVariable("account_id") String accountId) {
      var result = accountFacade.checkDuplicateAccount(accountId);
      return ResponseEntity.ok(BooleanResult.builder().result(result).build());
    }


    @Operation(summary="관리자 계정 변경")
    @PatchMapping("/{account_id:.+}")
  //  @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<AccountDto.AccountResponse> updateAccount(@Valid @PathVariable("account_id") String accountId, @RequestBody @Valid AccountDto.UpdateAccountRequest request) {
      var accountCommand = accountDtoMapper.of(request);
      var result = accountFacade.updateAccount(accountId, accountCommand);
      var response = accountDtoMapper.of(result);
      return ResponseEntity.ok(response);
    }

  @Operation(summary="관리자 비밀번호 변경")
  @PatchMapping("/{account_id:.+}/password")
  //  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<AccountDto.AccountResponse> changeAccountPw(@Valid @PathVariable("account_id") String accountId, @RequestBody @Valid AccountDto.UpdateAccountPWRequest request) {
    var accountCommand = accountDtoMapper.of(request);
    var result = accountFacade.changeAccountPw(accountId, accountCommand);
    var response = accountDtoMapper.of(result);
    return ResponseEntity.ok(response);
  }

    @Operation(summary="관리자 계정 삭제")
    @DeleteMapping("/{account_id:.+}")
  //  @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> DeleteAccount(@Valid @PathVariable("account_id") String accountId){
      accountFacade.deleteAccount(accountId);
      return ResponseEntity.noContent().build();
    }


}

