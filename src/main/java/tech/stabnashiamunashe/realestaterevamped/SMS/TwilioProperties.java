package tech.stabnashiamunashe.realestaterevamped.SMS;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "twilio")
public record TwilioProperties(String accountSid, String authToken, String phoneNumber) {
}

