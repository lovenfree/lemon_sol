package did.lemonaid.solution.infrastructure.credential;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import did.lemonaid.solution.domain.credential.Credential;
import did.lemonaid.solution.domain.credential.CredentialInfo;
import did.lemonaid.solution.interfaces.credential.CredentialDto;
import did.lemonaid.solution.interfaces.trustregistry.credential.TRCredentialDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
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
@Slf4j
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



  private  List<CredentialInfo.CredentialTRListInfo> getTRCredentials(TRCredentialDto.CredentialSearchCondition condition, Pageable pageable) {
    log.info("offset:"+pageable.getOffset()+", limit:"+pageable.getPageSize());
    return queryFactory.select(Projections.constructor(CredentialInfo.CredentialTRListInfo.class,
        credential.credentialDefinitionId, credential.credentialName,
        credential.schema.schemaId.as("schemaId"),
        credential.schema.schemaName.as("schemaName"),
        credential.credentialType, tenant.tenantName.as("tenantName")
      ))
      .from(credential)
      .innerJoin(credential.tenant, tenant)
      .innerJoin(credential.schema, schemas)
      .where(credentialTypeEq(condition.getCredentialType()),
        credDefIdContain(condition.getCredentialDefinitionId()),
        tenantIdEq(condition.getTenantId()),
         schemaNameContain(condition.getSchemaName()))
      .orderBy(credential.revisedDate.desc())
      .offset(pageable.getOffset())
      .limit(pageable.getPageSize())
      .fetch();
  }

  public Page<CredentialInfo.CredentialTRListInfo> retrieveTRCredentials(TRCredentialDto.CredentialSearchCondition condition, Pageable pageable) {
    var credentials = getTRCredentials(condition, pageable);
    var countQuery = queryFactory.select(Wildcard.count)
      .from(credential)
      .innerJoin(credential.tenant, tenant)
      .innerJoin(credential.schema, schemas)
      .where(credentialTypeEq(condition.getCredentialType()),
        credDefIdContain(condition.getCredentialDefinitionId()),
        tenantIdEq(condition.getTenantId()),
        schemaNameContain(condition.getSchemaName()));

    return PageableExecutionUtils.getPage(credentials, pageable, countQuery::fetchOne);

  }

  private BooleanExpression schemaNameContain(String schemaName) {
    return  !hasLength(schemaName) ? null : credential.schema.schemaName.contains(schemaName);
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

  private BooleanExpression tenantIdEq(String tenantId) {
    return !hasLength( tenantId)  ? null : credential.tenant.tenantId.eq(tenantId);
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
