package tech.stabnashiamunashe.realestaterevamped.Emails;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class EmailService {

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    public boolean isValidEmail(String email) {
        String hitEmailPattern = "^h2\\d{5}[a-zA-Z]@hit\\.ac\\.zw$";

        Pattern pattern = Pattern.compile(hitEmailPattern);
        Matcher matcher = pattern.matcher(email);

        return !matcher.matches();
    }

    public void sendEmailVerificationCode(String email, String verificationCode) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Email Verification");
            message.setText("Your verification code is " + verificationCode);
            javaMailSender.send(message);
        } catch (Exception e) {
            log.error("Error sending email verification code: {}", e.getMessage());
        }

    }
}
