package did.lemonaid.solution.domain.credential;

public interface SchemaSeriesFactory {
  Credential store(CredentialCommand.RegisterCredential command, String tenantId);
}
