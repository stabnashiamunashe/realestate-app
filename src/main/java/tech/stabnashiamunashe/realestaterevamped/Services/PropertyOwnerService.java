package tech.stabnashiamunashe.realestaterevamped.Services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.stabnashiamunashe.realestaterevamped.Emails.EmailService;
import tech.stabnashiamunashe.realestaterevamped.Models.PropertyOwner;
import tech.stabnashiamunashe.realestaterevamped.Repos.PropertyOwnerRepository;
import tech.stabnashiamunashe.realestaterevamped.Security.Models.UserRoles;
import tech.stabnashiamunashe.realestaterevamped.Security.Models.UserStatus;
import tech.stabnashiamunashe.realestaterevamped.Models.VerificationData;
import tech.stabnashiamunashe.realestaterevamped.Models.VerificationMedium;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class PropertyOwnerService {

    private final PropertyOwnerRepository propertyOwnerRepository;

    private final VerificationServices verificationService;

    private final EmailService emailService;

    private final PasswordEncoder passwordEncoder;

    public PropertyOwnerService(PropertyOwnerRepository propertyOwnerRepository, VerificationServices verificationService, EmailService emailService, PasswordEncoder passwordEncoder) {
        this.propertyOwnerRepository = propertyOwnerRepository;
        this.verificationService = verificationService;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    public PropertyOwner createPropertyOwner(PropertyOwner propertyOwner, VerificationMedium verificationMedium) throws Exception {
        if (emailService.isValidEmail(propertyOwner.getEmail())) {
            throw new IllegalArgumentException("Invalid email address");
        }

        if (propertyOwnerRepository.existsByEmail(propertyOwner.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        propertyOwner.setUserStatus(UserStatus.PENDING);
        propertyOwner.setUserRoles(List.of(UserRoles.LANDLORD));
        propertyOwner.setDateCreated(LocalDateTime.now());
        propertyOwner.setPassword(passwordEncoder.encode(propertyOwner.getPassword()));

        var savedPropertyOwner = propertyOwnerRepository.save(propertyOwner);

        var verificationData = switch (verificationMedium) {
            case EMAIL -> verificationService.sendVerificationCode(verificationMedium, propertyOwner.getEmail());
            case PHONE_NUMBER -> verificationService.sendVerificationCode(verificationMedium, propertyOwner.getPhoneNumber());
        };

        verificationData.setUserId(savedPropertyOwner.getId());
        verificationService.saveVerificationData(verificationData);
        return savedPropertyOwner;
    }

    public PropertyOwner updatePropertyOwner(PropertyOwner propertyOwner) {
        Optional<PropertyOwner> existingPropertyOwner = propertyOwnerRepository.findById(propertyOwner.getId()) ;
        if (existingPropertyOwner.isPresent()) {
            var existingPropertyOwnerData = existingPropertyOwner.get();
            existingPropertyOwnerData.setDateUpdated(LocalDateTime.now());

            return propertyOwnerRepository.save(existingPropertyOwnerData);
        }

        return null;

    }

    public Optional<PropertyOwner> getPropertyOwnerById(String id) {
        return propertyOwnerRepository.findById(id);
    }

    public List<PropertyOwner> getAllPropertyOwners() {
        return propertyOwnerRepository.findAll();
    }

    public void deletePropertyOwner(String id) {
        propertyOwnerRepository.deleteById(id);
    }

    public Boolean verifyPropertyOwner(String identifier, VerificationMedium verificationMedium, String verificationCode) {
        return switch (verificationMedium) {
            case EMAIL -> processVerification(identifier, verificationCode, propertyOwnerRepository::findByEmail);
            case PHONE_NUMBER -> processVerification(identifier, verificationCode, propertyOwnerRepository::findByPhoneNumber);
        };
    }


    private boolean processVerification(String identifier, String verificationCode, Function<String, Optional<PropertyOwner>> findByFunction) {
        Optional<PropertyOwner> propertyOwner = findByFunction.apply(identifier);

        if (propertyOwner.isPresent()) {
            PropertyOwner existingPropertyOwner = propertyOwner.get();
            VerificationData verificationData = verificationService.getVerificationDataByUserId(existingPropertyOwner.getId());

            if (verificationData.getVerificationCode().equals(verificationCode)) {
                verificationService.deleteVerificationData(existingPropertyOwner.getId());
                return true;
            }
        }

        return false;
    }

    public Optional<PropertyOwner> getPropertyOwnerByEmail(String email) {
        return propertyOwnerRepository.findByEmail(email);
    }

    public Long countPropertyOwners() {
        return propertyOwnerRepository.count();
    }
}
