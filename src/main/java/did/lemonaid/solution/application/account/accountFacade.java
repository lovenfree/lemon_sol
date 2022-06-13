package did.lemonaid.solution.application.account;

import did.lemonaid.solution.domain.account.AccountCommand;
import did.lemonaid.solution.domain.account.AccountInfo;
import did.lemonaid.solution.domain.account.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class accountFacade {
    private final AccountService accountService;

    public AccountInfo createAccount(AccountCommand command){
        var accountInfo = accountService.createAccount(command);
        return accountInfo;
    }


}
