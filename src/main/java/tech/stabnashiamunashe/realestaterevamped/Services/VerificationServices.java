package tech.stabnashiamunashe.realestaterevamped.Services;

import org.springframework.stereotype.Service;
import tech.stabnashiamunashe.realestaterevamped.Repos.VerificationDataRepository;
import tech.stabnashiamunashe.realestaterevamped.VerificationData;

@Service
public class VerificationServices {

    private final VerificationDataRepository verificationDataRepository;


    public VerificationServices(VerificationDataRepository verificationDataRepository) {
        this.verificationDataRepository = verificationDataRepository;
    }

    public VerificationData saveVerificationData(VerificationData verificationData) {
        VerificationData existingVerificationData = verificationDataRepository.findByUserId(verificationData.getUserId());
        if (existingVerificationData != null) {
            existingVerificationData.setVerificationCode(verificationData.getVerificationCode());
            return verificationDataRepository.save(existingVerificationData);
        }
        return verificationDataRepository.save(verificationData);
    }


    public VerificationData getVerificationDataByUserId(String userId) {
        return verificationDataRepository.findByUserId(userId);
    }

    public void deleteVerificationData(String id) {
        verificationDataRepository.deleteById(id);
    }
}
