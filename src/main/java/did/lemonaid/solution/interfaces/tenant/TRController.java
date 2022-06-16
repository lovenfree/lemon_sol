package did.lemonaid.solution.interfaces.tenant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name="Trust Registry")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class TRController {


    @GetMapping("/tenants")
    @Operation(summary = "Tenant List")
    public ResponseEntity<TrustRegistryDto.Tenant> retrieveTenant() {
        return null;
    }


    @Operation(summary = "certificates List")
    @GetMapping("/tenants/{tenant-id}/certificates")
    public ResponseEntity<TrustRegistryDto.Certificate> retrieveCertificates (@PathVariable("tenant-id") String tenantId) {

        return null;
    }

}
