package did.lemonaid.solution.infrastructure.tenant;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import did.lemonaid.solution.domain.tenant.Tenant;
import did.lemonaid.solution.interfaces.tenant.TenantDto;

import javax.persistence.EntityManager;
import java.util.List;

import static did.lemonaid.solution.domain.tenant.QTenant.tenant;
import static org.mapstruct.ap.internal.util.Strings.isEmpty;


public class TenantRepositoryImpl implements TenantRepositoryCustom{
  private final JPAQueryFactory queryFactory;

  public TenantRepositoryImpl(EntityManager em){
    this.queryFactory = new JPAQueryFactory(em);
  }

  @Override
  public List<Tenant> retrieveTenants(TenantDto.TenantSearchCondition condition) {

    return queryFactory.select(tenant)
      .from(tenant)
      .where(tenantNameContains(condition.getTenantName()),
        tenantDIDContains(condition.getTenantDID()),
        tenantTypeEq(condition.getTenantType()),
        tenantStatusEq(condition.getTenantStatus()))
      .orderBy(tenant.frstRegDttm.desc())
      .fetch();
  }

  private BooleanExpression tenantTypeEq(Tenant.TenantType tenantType) {
    return tenantType ==null ? null : tenant.tenantType.eq(tenantType);
  }

  private BooleanExpression tenantStatusEq(Tenant.TenantStatus tenantStatus) {
    return tenantStatus ==null ? null : tenant.tenantStatus.eq(tenantStatus);
  }

  private BooleanExpression tenantDIDContains(String tenantDID) {
    return isEmpty(tenantDID) ? null : tenant.tenantDID.contains(tenantDID);
  }

  private BooleanExpression tenantNameContains(String tenantName) {
    return isEmpty(tenantName) ? null : tenant.tenantName.contains(tenantName);
  }
}
