package tech.stabnashiamunashe.realestaterevamped.Repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import tech.stabnashiamunashe.realestaterevamped.Models.PropertyWishlist;

public interface PropertyWishlistRepository extends MongoRepository<PropertyWishlist, String> {
}
