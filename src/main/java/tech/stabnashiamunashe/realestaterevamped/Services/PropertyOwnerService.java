package tech.stabnashiamunashe.realestaterevamped.Services;

import org.springframework.stereotype.Service;
import tech.stabnashiamunashe.realestaterevamped.PropertyOwner;
import tech.stabnashiamunashe.realestaterevamped.Repos.PropertyOwnerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyOwnerService {

    private final PropertyOwnerRepository propertyOwnerRepository;

    public PropertyOwnerService(PropertyOwnerRepository propertyOwnerRepository) {
        this.propertyOwnerRepository = propertyOwnerRepository;
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
}
