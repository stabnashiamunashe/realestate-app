package tech.stabnashiamunashe.realestaterevamped.Repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import tech.stabnashiamunashe.realestaterevamped.Models.PropertyOwner;

import java.util.Optional;

public interface PropertyOwnerRepository extends MongoRepository<PropertyOwner, String> {
    Optional<PropertyOwner> findByEmail(String s);

    Optional<PropertyOwner> findByPhoneNumber(String s);

    boolean existsByEmail(String email);
}
