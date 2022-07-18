package did.lemonaid.solution.infrastructure.credential;

import did.lemonaid.solution.domain.credential.Credential;
import did.lemonaid.solution.domain.credential.CredentialInfo;
import did.lemonaid.solution.interfaces.credential.CredentialDto;
import did.lemonaid.solution.interfaces.trustregistry.credential.TRCredentialDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CredentialRepository extends JpaRepository<Credential, Long> {
  Optional<Credential> findByCredentialDefinitionId(String credentialDefinitionId);

  Optional<Credential> findByCredentialId(String credentialId);

  List<CredentialInfo.CredentialListInfo> retrieveCredentials(CredentialDto.CredentialSearchCondition condition);

//  List<CredentialInfo.CredentialTRListInfo> retrieveTRCredentials(TRCredentialDto.CredentialSearchCondition condition);

  Page<CredentialInfo.CredentialTRListInfo> retrieveTRCredentials(TRCredentialDto.CredentialSearchCondition condition, Pageable pageable);

}
