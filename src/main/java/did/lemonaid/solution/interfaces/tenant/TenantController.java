package did.lemonaid.solution.interfaces.tenant;

import did.lemonaid.solution.application.tenant.TenantFacade;
import did.lemonaid.solution.interfaces.trustregistry.TrustRegistryDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@Tag(name="Tenant", description = "Tenant API")
@RequiredArgsConstructor
@RequestMapping("/api/v1/tenant")
public class TenantController {
  private final TenantFacade tenantFacade;
  private final TenantDtoMapper tenantDtoMapper;


  @PostMapping
  public ResponseEntity<TenantDto.RegisterResponse> registerTenant(@RequestBody @Valid TenantDto.RegisterTenantRequest request) {
    var tenantCommand = tenantDtoMapper.of(request);
    var tenantId = tenantFacade.registerTenant(tenantCommand);
    var response = tenantDtoMapper.of(tenantId);
    return ResponseEntity.ok(response);
  }


  @GetMapping("/{tenant-id}")
  public ResponseEntity<TenantDto.TenantInfo> retrieve(@PathVariable("tenant-id") String tenantId) {
    var itemInfo = tenantFacade.re(itemToken);
    var response = itemDtoMapper.of(itemInfo);
    return CommonResponse.success(response);
  }



}
