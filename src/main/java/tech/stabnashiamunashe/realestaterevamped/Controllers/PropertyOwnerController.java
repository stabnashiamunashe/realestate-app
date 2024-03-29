package tech.stabnashiamunashe.realestaterevamped.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.stabnashiamunashe.realestaterevamped.Models.PropertyOwner;
import tech.stabnashiamunashe.realestaterevamped.Services.PropertyOwnerService;
import tech.stabnashiamunashe.realestaterevamped.Models.VerificationMedium;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/property-owner")
public class PropertyOwnerController {

    private final PropertyOwnerService propertyOwnerService;

    public PropertyOwnerController(PropertyOwnerService propertyOwnerService) {
        this.propertyOwnerService = propertyOwnerService;
    }

    @PostMapping("/register")
    public ResponseEntity<PropertyOwner> createPropertyOwner(@RequestBody PropertyOwner propertyOwner,@RequestParam VerificationMedium verificationMedium) throws Exception {
        PropertyOwner savedPropertyOwner = propertyOwnerService.createPropertyOwner(propertyOwner, verificationMedium);
        return ResponseEntity.ok(savedPropertyOwner);

    }

    @PostMapping("/count")
    public Long countPropertyOwners() {
        return propertyOwnerService.countPropertyOwners();
    }

    @PostMapping("/update")
    public ResponseEntity<PropertyOwner> updatePropertyOwner(@RequestBody PropertyOwner propertyOwner) {
        PropertyOwner updatedPropertyOwner = propertyOwnerService.updatePropertyOwner(propertyOwner);
        return ResponseEntity.ok(updatedPropertyOwner);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyOwner> getPropertyOwnerById(@PathVariable String id) {
        var propertyOwner = propertyOwnerService.getPropertyOwnerById(id);
        return propertyOwner.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/verify")
    public ResponseEntity<Boolean> verifyTenant(@RequestParam String identifier, VerificationMedium verificationMedium , String verificationCode) {
        return ResponseEntity.ok(propertyOwnerService.verifyPropertyOwner(identifier, verificationMedium, verificationCode));

    }

    @GetMapping("/all")
    public ResponseEntity<List<PropertyOwner>> getAllPropertyOwners() {
        List<PropertyOwner> propertyOwners = propertyOwnerService.getAllPropertyOwners();
        return ResponseEntity.ok(propertyOwners);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePropertyOwner(@PathVariable String id) {
        propertyOwnerService.deletePropertyOwner(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<PropertyOwner> getLoggedInOwnerDetails(Principal principal){
        var propertyOwner = propertyOwnerService.getPropertyOwnerByEmail(principal.getName());
        return propertyOwner.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
