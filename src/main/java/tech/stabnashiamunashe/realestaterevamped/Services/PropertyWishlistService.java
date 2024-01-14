package tech.stabnashiamunashe.realestaterevamped.Services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.stabnashiamunashe.realestaterevamped.Models.PropertyWishlist;
import tech.stabnashiamunashe.realestaterevamped.Repos.PropertyWishlistRepository;

@Service
public class PropertyWishlistService {

    private final PropertyWishlistRepository propertyWishlistRepository;

    public PropertyWishlistService(PropertyWishlistRepository propertyWishlistRepository) {
        this.propertyWishlistRepository = propertyWishlistRepository;
    }


    public ResponseEntity<?> addPropertyToWishlist(String propertyId, String userId) {
        propertyWishlistRepository.save(
                new PropertyWishlist(propertyId, userId)
        );

        return ResponseEntity.ok().build();
    }

}
