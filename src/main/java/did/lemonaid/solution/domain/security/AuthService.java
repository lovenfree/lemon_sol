package did.lemonaid.solution.domain.security;

public interface AuthService {
  String logIn(AuthCommand.LogIn command);
}
