package tech.stabnashiamunashe.realestaterevamped.Repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import tech.stabnashiamunashe.realestaterevamped.Models.Location;

public interface LocationRepository extends MongoRepository<Location, String> {
    boolean existsByName(String name);

    Location findByName(String name);
}
