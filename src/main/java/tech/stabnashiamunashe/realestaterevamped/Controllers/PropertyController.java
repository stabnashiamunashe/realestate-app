package tech.stabnashiamunashe.realestaterevamped.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.stabnashiamunashe.realestaterevamped.City;
import tech.stabnashiamunashe.realestaterevamped.DTOs.PropertyDTO;
import tech.stabnashiamunashe.realestaterevamped.Density;
import tech.stabnashiamunashe.realestaterevamped.Property;
import tech.stabnashiamunashe.realestaterevamped.PropertyType;
import tech.stabnashiamunashe.realestaterevamped.Services.PropertyService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping
    public ResponseEntity<Property> createProperty(
            @RequestParam("property") Property property,
            @RequestParam("images") @Nullable List<MultipartFile> images
    ) {
        Property savedProperty = propertyService.saveProperty(property, images);
        return new ResponseEntity<>(savedProperty, HttpStatus.CREATED);
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
    public ResponseEntity<Property> getPropertyById(@PathVariable String propertyId) {
        Optional<Property> property = propertyService.getProperty(propertyId);
        return property.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/ids")
    public ResponseEntity<List<Property>> getPropertiesByIds(@RequestParam List<String> propertyIds) {
        List<Property> properties = propertyService.getPropertiesByIds(propertyIds);
        return ResponseEntity.ok(properties);
    }



}
