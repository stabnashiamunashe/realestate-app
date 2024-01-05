package tech.stabnashiamunashe.realestaterevamped.Repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import tech.stabnashiamunashe.realestaterevamped.Tenant;

public interface TenantRepository extends MongoRepository<Tenant, String> {
}
