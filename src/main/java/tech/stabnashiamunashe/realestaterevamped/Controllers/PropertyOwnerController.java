package tech.stabnashiamunashe.realestaterevamped.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.stabnashiamunashe.realestaterevamped.PropertyOwner;
import tech.stabnashiamunashe.realestaterevamped.Services.PropertyOwnerService;

import java.util.List;

@RestController
@RequestMapping("/api/property-owner")
public class PropertyOwnerController {

    private final PropertyOwnerService propertyOwnerService;

    public PropertyOwnerController(PropertyOwnerService propertyOwnerService) {
        this.propertyOwnerService = propertyOwnerService;
    }

    @PostMapping("/create")
    public ResponseEntity<PropertyOwner> createPropertyOwner(@RequestBody PropertyOwner propertyOwner) {
        PropertyOwner savedPropertyOwner = propertyOwnerService.createPropertyOwner(propertyOwner);
        return ResponseEntity.ok(savedPropertyOwner);

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
}
