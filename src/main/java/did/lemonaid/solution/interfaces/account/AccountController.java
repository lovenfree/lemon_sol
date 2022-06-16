package did.lemonaid.solution.interfaces.account;

import did.lemonaid.solution.domain.account.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Tag(name="Account")
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@Slf4j
public class AccountController {

    private final AccountService accountService;
    private final AccountDtoMapper mapper;

    @Operation(summary = "관리자 계정 생성")
    @PostMapping("/accounts")
    public ResponseEntity<AccountDto.RegisterResponse> createAccount(@RequestBody @Valid AccountDto.RegisterRequest request) {
        var command =  mapper.of(request);
        var accountInfo = accountService.createAccount(command);
        return ResponseEntity.ok(mapper.of(accountInfo));
    }

}

