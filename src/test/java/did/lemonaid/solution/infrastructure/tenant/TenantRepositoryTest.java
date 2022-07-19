package did.lemonaid.solution.infrastructure.tenant;

import did.lemonaid.solution.common.service.AccountAuditorAware;
import did.lemonaid.solution.domain.tenant.Tenant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@ActiveProfiles("test")
class TenantRepositoryTest {
  @MockBean
  JpaAud accountAuditorAware;
  @Autowired
  TestEntityManager testEntityManager;
  @Autowired
  private TenantRepository tenantRepository;
  private Tenant testTenant;


  @BeforeEach
  void setUp(){
    testTenant = generateTenant();
    testTenant = testEntityManager.persist(testTenant);
  }


  @Test
  @DisplayName("Tenant Id 로 Tenant 검색")
  void findByTenantId() {
    // when
    var result = tenantRepository.findByTenantId(testTenant.getTenantId()).get();

    // then
    assertThat(result.getTenantName()).isEqualTo("LG CNS ISSUER");
    assertThat(result.getTenantStatus()).isEqualTo(Tenant.TenantStatus.PAUSE);
    System.out.println(testTenant.getTenantId());
  }

  private Tenant generateTenant() {
    return Tenant.builder().tenantName("LG CNS ISSUER")
      .tenantType(Tenant.TenantType.ISSUER)
      .trustTenant(true)
      .build();
  }

}
