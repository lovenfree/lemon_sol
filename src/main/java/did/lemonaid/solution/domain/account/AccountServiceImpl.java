package did.lemonaid.solution.domain.account;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final AccountStore accountStore;
    private final AccountReader accountReader;

    @Transactional
    public AccountInfo createAccount(AccountCommand command){
        var iniAccount = command.toEntity();
        Account account = accountStore.store(iniAccount);
        return new AccountInfo(account);
    }

}
