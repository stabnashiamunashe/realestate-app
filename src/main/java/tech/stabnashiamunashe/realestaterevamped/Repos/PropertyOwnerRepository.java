package tech.stabnashiamunashe.realestaterevamped.Repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import tech.stabnashiamunashe.realestaterevamped.PropertyOwner;

public interface PropertyOwnerRepository extends MongoRepository<PropertyOwner, String> {
}
