package tech.stabnashiamunashe.realestaterevamped.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.stabnashiamunashe.realestaterevamped.Models.City;
import tech.stabnashiamunashe.realestaterevamped.DTOs.PropertyDTO;
import tech.stabnashiamunashe.realestaterevamped.Models.Density;
import tech.stabnashiamunashe.realestaterevamped.Models.Property;
import tech.stabnashiamunashe.realestaterevamped.Models.PropertyType;
import tech.stabnashiamunashe.realestaterevamped.Services.PropertyService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_OWNER','SCOPE_ADMIN','SCOPE_AGENT')")
    @PostMapping
    public ResponseEntity<PropertyDTO> createProperty(
            @ModelAttribute("property") Property property,
            @RequestParam("images") @Nullable List<MultipartFile> images,
            Principal principal
    ) {
        PropertyDTO savedProperty = propertyService.saveProperty(property, images, principal.getName());
        return new ResponseEntity<>(savedProperty, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_OWNER','SCOPE_ADMIN','SCOPE_AGENT')")
    @PostMapping("/{propertyId}/ownership-documents")
    public ResponseEntity<PropertyDTO> addOwnershipDocuments(
            @PathVariable String propertyId,
            @RequestParam("documents") List<MultipartFile> documents
    ) {
        propertyService.addPropertyOwnershipDocuments(propertyId, documents);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_OWNER','SCOPE_ADMIN','SCOPE_AGENT')")
    @PostMapping("/count")
    public Long countProperties() {
        return propertyService.countProperties();
    }

    @GetMapping("/search")
    public List<PropertyDTO> findProperty(@RequestParam @Nullable Integer bedrooms,
                                          @RequestParam @Nullable Integer bathrooms,
                                          @RequestParam @Nullable Double minPrice,
                                          @RequestParam @Nullable Double maxPrice,
                                          @RequestParam @Nullable PropertyType propertyType,
                                          @RequestParam @Nullable Density density,
                                          @RequestParam @Nullable String location,
                                          @RequestParam @Nullable City city
    ){

        return propertyService.findPropertyByCriteriaSearch(
                bedrooms,
                bathrooms,
                minPrice,
                maxPrice,
                propertyType,
                density,
                location,
                city
        );
    }

    @GetMapping("/{propertyId}")
    public ResponseEntity<PropertyDTO> getPropertyById(@PathVariable String propertyId) {
        Optional<Property> property = propertyService.getProperty(propertyId);

        return property.map(value -> new ResponseEntity<>(new PropertyDTO(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/ids")
    public ResponseEntity<List<PropertyDTO>> getPropertiesByIds(@RequestParam List<String> propertyIds) {
        List<PropertyDTO> properties = propertyService.getPropertiesByIds(propertyIds);
        return ResponseEntity.ok(properties);
    }



}
