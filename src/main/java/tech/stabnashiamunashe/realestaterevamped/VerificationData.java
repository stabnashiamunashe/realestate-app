package tech.stabnashiamunashe.realestaterevamped;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "verification_data")
public class VerificationData {

    private String id;
    private String verificationCode;
    @Enumerated(EnumType.STRING)
    private VerificationMedium verificationMedium;
    private String userId;
}
