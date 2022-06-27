package did.lemonaid.solution.domain.tenant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class TenantServiceImplTest {
  private TenantService tenantService;

  @Mock
  private TenantReader tenantReader;

  @Mock
  private TenantStore tenantStore;

  @Mock
  private TenantInfoMapper tenantInfoMapper;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.initMocks(this);
    tenantService = new TenantServiceImpl(tenantReader, tenantStore, tenantInfoMapper);
  }

  @Test
  @DisplayName("Tenant 등록")
  public void registerTenant() {

  }


}
