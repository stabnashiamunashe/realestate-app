package tech.stabnashiamunashe.realestaterevamped.Repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import tech.stabnashiamunashe.realestaterevamped.Security.Models.UserStatus;
import tech.stabnashiamunashe.realestaterevamped.Models.Tenant;

import java.util.List;
import java.util.Optional;

public interface TenantRepository extends MongoRepository<Tenant, String> {
    List<Tenant> findByUserStatus(UserStatus userStatus);

    Optional<Tenant> findByEmail(String identifier);

    Optional<Tenant> findByPhoneNumber(String identifier);

    boolean existsByEmail(String email);
}
