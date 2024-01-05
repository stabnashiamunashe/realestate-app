package tech.stabnashiamunashe.realestaterevamped.Services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import tech.stabnashiamunashe.realestaterevamped.Emails.EmailService;
import tech.stabnashiamunashe.realestaterevamped.Repos.VerificationDataRepository;
import tech.stabnashiamunashe.realestaterevamped.SMS.TwilioService;
import tech.stabnashiamunashe.realestaterevamped.VerificationData;
import tech.stabnashiamunashe.realestaterevamped.VerificationMedium;

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

    private int generateVerificationCode(){
        Random rand = new Random();
        return rand.nextInt(999999);
    }

    public boolean sendVerificationCode(VerificationMedium verificationMedium, String identifier) throws Exception {
        int verificationCode = generateVerificationCode();
        return switch (verificationMedium) {
            case EMAIL -> emailService.sendEmailVerificationCode(identifier, verificationCode);
            case PHONE_NUMBER -> twilioService.sendSmsVerificationCode(identifier, verificationCode);
            default -> false;
        };
    }
}
