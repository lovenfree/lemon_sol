package did.lemonaid.solution.interfaces.trustregistry;

import com.fasterxml.jackson.databind.ObjectMapper;
import did.lemonaid.solution.application.credential.CredentialFacade;
import did.lemonaid.solution.application.tenant.TenantFacade;
import did.lemonaid.solution.domain.credential.Credential;
import did.lemonaid.solution.domain.credential.schema.SchemaAttribute;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    mockMvc.perform(MockMvcRequestBuilders.get(PATH+"/tenants"))
      .andDo(print())
      .andExpect(status().isOk());
  }

  @Test
  @DisplayName("테넌트 활성화 테스트")
  void activateTenant() {
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


    mockMvc.perform(MockMvcRequestBuilders.post(PATH+"/credentials")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(credential)))
      .andDo(print())
      .andExpect(status().isOk());
  }

  @Test
  void updateCredential() {
  }

  @Test
  void retrieveCertificate() {
  }
}
