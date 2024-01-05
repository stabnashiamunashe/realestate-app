package tech.stabnashiamunashe.realestaterevamped;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import tech.stabnashiamunashe.realestaterevamped.SMS.TwilioProperties;
import tech.stabnashiamunashe.realestaterevamped.Security.Configss.RSAKeyProperties;
import tech.stabnashiamunashe.realestaterevamped.Services.S3Properties;

@EnableConfigurationProperties({TwilioProperties.class, S3Properties.class, RSAKeyProperties.class})
@SpringBootApplication
public class RealestateRevampedApplication {

    public static void main(String[] args) {
        SpringApplication.run(RealestateRevampedApplication.class, args);
    }

}
