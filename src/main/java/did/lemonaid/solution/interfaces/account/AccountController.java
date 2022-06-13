package did.lemonaid.solution.interfaces.account;

import did.lemonaid.solution.domain.account.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@Api(tags = {"Account"})
@Slf4j
@EnableSwagger2
public class AccountController {

    private final AccountService accountService;
    private final AccountDtoMapper mapper;

    @ApiOperation(value = "관리자 계정 생성")
    @RequestMapping(value = "/accounts", method = RequestMethod.POST)
    public ResponseEntity<AccountDto.RegisterResponse> createAccount(@RequestBody @Valid AccountDto.RegisterRequest request) {
        var command =  mapper.of(request);
        var accountInfo = accountService.createAccount(command);
        return ResponseEntity.ok(mapper.of(accountInfo));
    }

}

