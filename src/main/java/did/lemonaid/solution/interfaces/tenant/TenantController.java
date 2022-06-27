package did.lemonaid.solution.interfaces.tenant;

import did.lemonaid.solution.application.tenant.TenantFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@Tag(name="Tenant", description = "Admin Tenant API")
@RequiredArgsConstructor
@RequestMapping("/api/v1/tenants")
public class TenantController {
  private final TenantFacade tenantFacade;
  private final TenantDtoMapper tenantDtoMapper;


  @PostMapping
  @Operation(summary="Register Tenant")
  public ResponseEntity<TenantDto.TenantResponse> registerTenant(@RequestBody @Valid TenantDto.RegisterTenantRequest request) {
    var tenantCommand = tenantDtoMapper.of(request);
    var tenantId = tenantFacade.registerTenant(tenantCommand);
    var response = tenantDtoMapper.of(tenantId);
    return ResponseEntity.ok(response);
  }

  @PatchMapping ("/{tenant-id}")
  @Operation(summary="Update Tenant")
  public ResponseEntity<TenantDto.TenantResponse> updateTenant(@Valid @PathVariable("tenant-id") String tenantId, @RequestBody @Valid TenantDto.UpdateTenantRequest request) {
    var tenantCommand = tenantDtoMapper.of(request);
    var result = tenantFacade.updateTenantInfo(tenantId, tenantCommand);
    var response = tenantDtoMapper.of(result);
    return ResponseEntity.ok(response);
  }



  @GetMapping("/{tenant-id}")
  @Operation(summary="Tenant details")
  public ResponseEntity<TenantDto.TenantInfo> retrieveTenant(@PathVariable("tenant-id") String tenantId) {
    var tenantInfo = tenantFacade.retrieveTenant(tenantId);
    var response = tenantDtoMapper.of(tenantInfo);
    return ResponseEntity.ok(response);
  }

  @GetMapping
  @Operation(summary="Tenant list")
  public ResponseEntity<List<TenantDto.TenantInfo>> retrieveTenants() {
    var tenantInfos = tenantFacade.retrieveTenants();
    var response = tenantDtoMapper.of(tenantInfos);
    return ResponseEntity.ok(response);
  }



}
