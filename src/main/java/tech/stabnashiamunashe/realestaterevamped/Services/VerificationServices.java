package tech.stabnashiamunashe.realestaterevamped.Services;

import org.springframework.stereotype.Service;
import tech.stabnashiamunashe.realestaterevamped.Emails.EmailService;
import tech.stabnashiamunashe.realestaterevamped.Repos.VerificationDataRepository;
import tech.stabnashiamunashe.realestaterevamped.SMS.TwilioService;
import tech.stabnashiamunashe.realestaterevamped.Models.VerificationData;
import tech.stabnashiamunashe.realestaterevamped.Models.VerificationMedium;

import java.util.Random;

@Service
public class VerificationServices {

    private final VerificationDataRepository verificationDataRepository;

    private final EmailService emailService;

    private final TwilioService twilioService;


    public VerificationServices(VerificationDataRepository verificationDataRepository, EmailService emailService, TwilioService twilioService) {
        this.verificationDataRepository = verificationDataRepository;
        this.emailService = emailService;
        this.twilioService = twilioService;
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

    private String generateVerificationCode(){
        Random rand = new Random();
        return String.format("%04d", rand.nextInt(999999));
    }

    public VerificationData sendVerificationCode(VerificationMedium verificationMedium, String identifier) {
        String verificationCode = generateVerificationCode();
        switch (verificationMedium) {
            case EMAIL -> emailService.sendEmailVerificationCode(identifier, verificationCode);
            case PHONE_NUMBER -> twilioService.sendSmsVerificationCode(identifier, verificationCode);
            default -> {
            }
        }

        VerificationData verificationData = new VerificationData();
        verificationData.setVerificationCode(verificationCode);
        verificationData.setVerificationMedium(verificationMedium);

        return verificationData;
    }
}
