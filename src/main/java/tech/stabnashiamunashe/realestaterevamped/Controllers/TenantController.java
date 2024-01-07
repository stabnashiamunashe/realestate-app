package tech.stabnashiamunashe.realestaterevamped.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.stabnashiamunashe.realestaterevamped.Services.TenantService;
import tech.stabnashiamunashe.realestaterevamped.Models.Tenant;
import tech.stabnashiamunashe.realestaterevamped.Models.VerificationMedium;

import java.util.List;

@RestController
@RequestMapping("/api/tenant")
public class TenantController {

    private final TenantService tenantService;

    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> createTenant(@RequestBody Tenant tenant,@RequestParam VerificationMedium verificationMedium) throws Exception {
        return tenantService.createTenant(tenant, verificationMedium);
    }

    @PostMapping("/verify")
    public ResponseEntity<Boolean> verifyTenant(@RequestParam String identifier, VerificationMedium verificationMedium , String verificationCode) {
        return ResponseEntity.ok(tenantService.verifyTenant(identifier, verificationMedium, verificationCode));

    }

    @GetMapping("/{id}")
    public ResponseEntity<Tenant> getTenantById(@PathVariable String id) {
        var tenant = tenantService.getTenantById(id);
        return tenant.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countTenants() {
        return ResponseEntity.ok(tenantService.countTenants());
    }

    @PutMapping("/update")
    public ResponseEntity<Tenant> updateTenant(@RequestBody Tenant tenant) {
        Tenant updatedTenant = tenantService.updateTenant(tenant);
        return ResponseEntity.ok(updatedTenant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTenant(@PathVariable String id) {
        tenantService.deleteTenant(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Tenant>> getAllTenants() {
        List<Tenant> tenants = tenantService.getAllTenants();
        return ResponseEntity.ok(tenants);
    }


}
