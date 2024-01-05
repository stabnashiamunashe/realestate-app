package tech.stabnashiamunashe.realestaterevamped.Services;

import org.springframework.stereotype.Service;
import tech.stabnashiamunashe.realestaterevamped.PropertyOwner;
import tech.stabnashiamunashe.realestaterevamped.Repos.PropertyOwnerRepository;
import tech.stabnashiamunashe.realestaterevamped.Tenant;
import tech.stabnashiamunashe.realestaterevamped.VerificationData;
import tech.stabnashiamunashe.realestaterevamped.VerificationMedium;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class PropertyOwnerService {

    private final PropertyOwnerRepository propertyOwnerRepository;

    private final VerificationServices verificationService;

    public PropertyOwnerService(PropertyOwnerRepository propertyOwnerRepository, VerificationServices verificationService) {
        this.propertyOwnerRepository = propertyOwnerRepository;
        this.verificationService = verificationService;
    }

    public PropertyOwner createPropertyOwner(PropertyOwner propertyOwner) {
        return propertyOwnerRepository.save(propertyOwner);
    }

    public PropertyOwner updatePropertyOwner(PropertyOwner propertyOwner) {
        Optional<PropertyOwner> existingPropertyOwner = propertyOwnerRepository.findById(propertyOwner.getId()) ;
        assert existingPropertyOwner.isPresent();
        existingPropertyOwner.get().setFirstName(propertyOwner.getFirstName());
        return propertyOwnerRepository.save(existingPropertyOwner.get());
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
            default -> false;
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
}
