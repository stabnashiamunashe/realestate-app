package tech.stabnashiamunashe.realestaterevamped.Repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import tech.stabnashiamunashe.realestaterevamped.PropertyOwner;
import tech.stabnashiamunashe.realestaterevamped.Tenant;

import java.util.Optional;

public interface PropertyOwnerRepository extends MongoRepository<PropertyOwner, String> {
    Optional<PropertyOwner> findByEmail(String s);

    Optional<PropertyOwner> findByPhoneNumber(String s);
}
