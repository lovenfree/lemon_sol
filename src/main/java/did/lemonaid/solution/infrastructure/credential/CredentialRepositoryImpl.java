package did.lemonaid.solution.infrastructure.credential;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import did.lemonaid.solution.domain.credential.Credential;
import did.lemonaid.solution.domain.credential.CredentialInfo;
import did.lemonaid.solution.domain.tenant.Tenant;
import did.lemonaid.solution.interfaces.credential.CredentialDto;
import did.lemonaid.solution.interfaces.tenant.TenantDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static did.lemonaid.solution.domain.credential.QCredential.credential;
import static did.lemonaid.solution.domain.credential.schema.QSchemas.schemas;
import static did.lemonaid.solution.domain.tenant.QTenant.tenant;
import static org.springframework.util.StringUtils.hasLength;
import static org.springframework.util.StringUtils.isEmpty;

@Repository
@RequiredArgsConstructor
public class CredentialRepositoryImpl {
  private final JPAQueryFactory queryFactory;

  public List<CredentialInfo.CredentialListInfo> retrieveCredentials(CredentialDto.CredentialSearchCondition condition) {
      return queryFactory.select(Projections.constructor(CredentialInfo.CredentialListInfo.class,
          tenant.tenantId.as("tenantId"),tenant.tenantDID.as("tenantDID"), tenant.tenantName.as("tenantName"),
          credential.credentialId, credential.credentialDefinitionId, credential.credentialName, credential.credentialType,
          credential.expiryDateYN, credential.trustCredentialYN,
          credential.schema.schemaId.as("schemaId"), 
          credential.validityDays, credential.revisedDate
        ))
        .from(credential)
        .innerJoin(credential.tenant, tenant)
        .innerJoin(credential.schema, schemas)
        .where(tenantNameContain(condition.getTenantName()),
          credentialNameContain(condition.getCredentialName()),
          credentialTypeEq(condition.getCredentialType()),
          credDefIdContain(condition.getCredentialDefinitionId()),
          expiryDateYN(condition.getExpiryDateYN()),
          trustCredentialYN(condition.getTrustCredentialYN()),
          dateBetween(condition.getSearchStartDate(), condition.getSearchEndDate()))
        .orderBy(credential.revisedDate.desc())
        .fetch();
    }

  private BooleanExpression tenantNameContain(String tenantName) {
    return  !hasLength(tenantName) ? null : credential.tenant.tenantName.contains(tenantName);
  }

  private BooleanExpression credentialNameContain(String credentialName) {
    return  !hasLength(credentialName) ? null : credential.credentialName.contains(credentialName);
  }


    private BooleanExpression credentialTypeEq(Credential.CredentialType credentialType) {
      return isEmpty( credentialType)  ? null : credential.credentialType.eq(credentialType);
    }

  private BooleanExpression credDefIdContain(String credDefId) {
    return  !hasLength(credDefId) ? null : credential.credentialDefinitionId.contains(credDefId);
  }


    private BooleanExpression expiryDateYN(Boolean expiryDateYN) {
      return isEmpty(expiryDateYN) ? null : credential.expiryDateYN.eq(expiryDateYN);
    }

  private BooleanExpression trustCredentialYN(Boolean trustCredentialYN) {
    return isEmpty(trustCredentialYN) ? null : credential.trustCredentialYN.eq(trustCredentialYN);
  }

  private BooleanExpression dateBetween(LocalDateTime searchStartDate, LocalDateTime searchEndDate) {
      if(searchStartDate == null || searchEndDate ==null){
        return null;
      }
      return credential.revisedDate.between(searchStartDate, searchEndDate);
    }







}
