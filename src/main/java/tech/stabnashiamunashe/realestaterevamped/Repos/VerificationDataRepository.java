package tech.stabnashiamunashe.realestaterevamped.Repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import tech.stabnashiamunashe.realestaterevamped.Models.VerificationData;

public interface VerificationDataRepository extends MongoRepository<VerificationData, String> {
    VerificationData findByUserId(String userId);
}
