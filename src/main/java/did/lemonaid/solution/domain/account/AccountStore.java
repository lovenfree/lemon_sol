package did.lemonaid.solution.domain.account;

public interface AccountStore {
    Account store(Account initAccount);
    Account initStore(Account initAccount);

}
