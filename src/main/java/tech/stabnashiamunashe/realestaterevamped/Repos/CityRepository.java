package tech.stabnashiamunashe.realestaterevamped.Repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import tech.stabnashiamunashe.realestaterevamped.Models.City;

public interface CityRepository extends MongoRepository<City, String> {
    boolean existsByName(String name);

    City findByName(String name);
}
