package tech.stabnashiamunashe.realestaterevamped.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tech.stabnashiamunashe.realestaterevamped.Services.PropertyWishlistService;

public class PropertyWishlistController {

    private final PropertyWishlistService propertyWishlistService;

    public PropertyWishlistController(PropertyWishlistService propertyWishlistService) {
        this.propertyWishlistService = propertyWishlistService;
    }

    @PostMapping("/add/{userId}")
    public ResponseEntity<?> addPropertyToWishlist(@RequestParam String propertyId, @PathVariable String userId) {
        return propertyWishlistService.addPropertyToWishlist(propertyId, userId);
    }
}
