package tech.stabnashiamunashe.realestaterevamped.Security.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import tech.stabnashiamunashe.realestaterevamped.Security.Models.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

}
