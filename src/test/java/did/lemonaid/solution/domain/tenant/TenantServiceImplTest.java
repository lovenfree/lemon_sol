package did.lemonaid.solution.domain.tenant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
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
    //given

    //when

    //then

  }


}
