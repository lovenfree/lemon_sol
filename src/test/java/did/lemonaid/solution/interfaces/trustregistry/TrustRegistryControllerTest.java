package did.lemonaid.solution.interfaces.trustregistry;

import com.fasterxml.jackson.databind.ObjectMapper;
import did.lemonaid.solution.application.credential.CredentialFacade;
import did.lemonaid.solution.application.tenant.TenantFacade;
import did.lemonaid.solution.domain.credential.Credential;
import did.lemonaid.solution.domain.credential.CredentialCommand;
import did.lemonaid.solution.domain.credential.schema.SchemaAttribute;
import did.lemonaid.solution.domain.tenant.Tenant;
import did.lemonaid.solution.domain.tenant.TenantInfo;
import did.lemonaid.solution.interfaces.tenant.TenantDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TrustRegistryController.class)
public class TrustRegistryControllerTest {
  private final String PATH = "/api/v1/trust-registry";
  @MockBean
  private TenantFacade tenantFacade;

  @MockBean
  private CredentialFacade credentialFacade;

  @MockBean
  private TrustRegistryDtoMapper mapper;

  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private MockMvc mockMvc;



  @Test
  @DisplayName("테넌트 목록 조회")
  void retrieveTenants() throws Exception {

    var testL = createTenantTestData();
    given(tenantFacade.retrieveTenants()).willReturn(testL);

    mockMvc.perform(MockMvcRequestBuilders.get(PATH+"/tenants"))
      .andDo(print())
      .andExpect(status().isOk());
//      .andExpect(content());
  }

  private  List<TenantInfo.TenantDetail> createTenantTestData() {
//    var tenant1 = TenantInfo.TenantDetail.builder().tenantId("tnt_askjeigjksldkfjh")
//      .tenantType(Tenant.TenantType.ISSUER)
//      .tenantName("Test tenant #1")
//      .tenantDID("RTHAnR3aKM5iSNmHnr4am4").tenantStatus(Tenant.TenantStatus.ACTIVATE).build();
//    var tenant2 =  TenantInfo.TenantDetail.builder().tenantId("tnt_askjeigjksldkfjh")
//      .tenantType(Tenant.TenantType.ISSUER)
//      .tenantName("Test tenant #2")
//      .tenantDID("RTHAnR3aKM5iSNmHnr4am5").tenantStatus(Tenant.TenantStatus.ACTIVATE).build();
//    List<TenantInfo.TenantDetail> tenants = new ArrayList<>();
//    tenants.add(tenant1);
//    tenants.add(tenant2);
//    return tenants;
    return null;
  }

  @Test
  @DisplayName("테넌트 활성화 테스트")
  void activateTenant() throws Exception {
    var request = TrustRegistryDto.ActivateTenantRequest.builder().tenantWalletId("test-wallet-id")
      .tenantDID("tenant-did").tenantInvitationUrl("http://djkjsnmxn,fmalsk").build();
    var tenantID = "tnt_askjeigjksldkfjh";

    given(tenantFacade.activateTenant(tenantID, mapper.activateOf(request))).willReturn(tenantID);

    mockMvc.perform(MockMvcRequestBuilders.post(PATH+"/tenants/{tenant-id}/activate",tenantID))
      .andExpect(status().isOk())
      .andExpect(jsonPath(tenantID).exists())
      .andDo(print());

  }

  @Test
  @DisplayName("테넌트 활성화 fail test - param invalid")
  void activateTenant_EXCEPTION() {

  }

  @Test
  @DisplayName("테넌트 활성화 fail test - 존재 하지 않는 테넌트")
  void activateTenant_EXCEPTION_Tenant() {

  }

  @Test
  void retrieveCertificates() {
  }

  @Test
  @DisplayName("증명서 등록 api")
  public void registerCredential() throws Exception {
    //test Data
    List<TrustRegistryDto.RegisterSchemaAttribute> schemaAttributeList = new ArrayList<>();
    var schemaattr = TrustRegistryDto.RegisterSchemaAttribute.builder()
      .attributeCode("name")
      .attributeCode("name")
      .mimeType(SchemaAttribute.MimeType.TEXT_PLAIN).build();
     schemaAttributeList.add(schemaattr);
    var schema = TrustRegistryDto.RegisterSchemaInfo.builder()
      .schemaId("schemaID")
      .schemaName("ID")
      .schemaAttributeList(schemaAttributeList).build();

    var credential = TrustRegistryDto.RegisterCredentialRequest.builder()
        .tenantId("tenantID").credentialId("credentialID").credentialName("name").credentialType(Credential.CredentialType.EMPLOYEE_ID)
        .credentialDefinitionId("cred_def_id").authLinkUrl("http://alskdjfklkjalk").expiryDateYN(false).templateItemMapping("test:laskjdf").schema(schema).build();

//    var response = TrustRegistryDto.CredentialResponse.builder().credentialId("credentialID").build();

    given(credentialFacade.registerCredential(mapper.of(credential),"tenantID")).willReturn("credentialID");
//    when(, "tenantID")).thenReturn(response);

    mockMvc.perform(MockMvcRequestBuilders.post(PATH+"/credentials")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(credential)))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().string (containsString("credentialID")));
  }

  @Test
  @DisplayName("증명서 등록 api")
  void updateCredential() {
  }

  @Test
  void retrieveCertificate() {
  }
}
