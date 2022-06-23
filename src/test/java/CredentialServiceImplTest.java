import did.lemonaid.solution.infrastructure.credential.CredentialRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CredentialServiceImplTest {

  @Test
  void registerCredential() {
  }

  @Test
  void retrieveCredentials() {
  }

  @Test
  void retrieveCredential() {
  }

  @Test
  @DisplayName("Update credential")
  void updateCredential() {

    //given
    //CredentialCommand.UpdateCredential updateCredential = new CredentialCommand.UpdateCredential();

    //when

    //then

  }
}
