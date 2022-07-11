package did.lemonaid.solution.infrastructure.tenant;


import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import did.lemonaid.solution.domain.tenant.Tenant;
import did.lemonaid.solution.interfaces.tenant.TenantDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static org.springframework.util.StringUtils.hasLength;

import java.util.List;

import static did.lemonaid.solution.domain.tenant.QTenant.tenant;


@Repository
@RequiredArgsConstructor
public class TenantRepositoryImpl {
  private final JPAQueryFactory queryFactory;

  public List<Tenant> retrieveTenants(TenantDto.TenantSearchCondition condition) {

    return queryFactory.select(tenant)
      .from(tenant)
      .where(tenantNameContains(condition.getTenantName()),
        tenantDIDContains(condition.getTenantDID()),
        tenantTypeEq(condition.getTenantType()),
        tenantStatusEq(condition.getTenantStatus()))
      .orderBy(tenant.registrationDate.desc())
      .fetch();
  }

  public List<Tenant> retrieveActiveTenants() {
    return queryFactory.select(tenant)
      .from(tenant)
      .where(
        tenantStatusEq(Tenant.TenantStatus.ACTIVATE))
      .orderBy(tenant.registrationDate.desc())
      .fetch();
  }

  public List<Tenant> retrieveActiveIssuers() {
    return queryFactory.select(tenant)
      .from(tenant)
      .where(
        tenantTypeEq(Tenant.TenantType.ISSUER).or(tenantTypeEq(Tenant.TenantType.BOTH)),
        tenantStatusEq(Tenant.TenantStatus.ACTIVATE))
      .orderBy(tenant.registrationDate.desc())
      .fetch();
  }

  public List<Tenant> retrieveActiveVerifiers() {
    return queryFactory.select(tenant)
      .from(tenant)
      .where(
        tenantTypeEq(Tenant.TenantType.VERIFIER).or(tenantTypeEq(Tenant.TenantType.BOTH)),
        tenantStatusEq(Tenant.TenantStatus.ACTIVATE))
      .orderBy(tenant.registrationDate.desc())
      .fetch();
  }


  private BooleanExpression tenantTypeEq(Tenant.TenantType tenantType) {
    return tenantType ==null ? null : tenant.tenantType.eq(tenantType);
  }

  private BooleanExpression tenantStatusEq(Tenant.TenantStatus tenantStatus) {
    return tenantStatus ==null ? null : tenant.tenantStatus.eq(tenantStatus);
  }

  private BooleanExpression tenantDIDContains(String tenantDID) {
    return !hasLength(tenantDID) ? null : tenant.tenantDID.contains(tenantDID);
  }

  private BooleanExpression tenantNameContains(String tenantName) {
    return !hasLength(tenantName) ? null : tenant.tenantName.contains(tenantName);
  }


}
