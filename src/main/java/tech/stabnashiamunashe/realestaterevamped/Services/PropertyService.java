package tech.stabnashiamunashe.realestaterevamped.Services;

import jakarta.annotation.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tech.stabnashiamunashe.realestaterevamped.Models.City;
import tech.stabnashiamunashe.realestaterevamped.DTOs.PropertyDTO;
import tech.stabnashiamunashe.realestaterevamped.Models.Density;
import tech.stabnashiamunashe.realestaterevamped.Models.Property;
import tech.stabnashiamunashe.realestaterevamped.Models.PropertyType;
import tech.stabnashiamunashe.realestaterevamped.Repos.PropertyRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;

    private final S3Service s3Service;

    public PropertyService(PropertyRepository propertyRepository, S3Service s3Service) {
        this.propertyRepository = propertyRepository;
        this.s3Service = s3Service;
    }


    public PropertyDTO saveProperty(Property property,@Nullable List<MultipartFile> images) {

        property.setDateCreated(LocalDateTime.now());
        if(images != null) {
            List<String> imageUrls = images.stream()
                    .map(s3Service::uploadFile)
                    .toList();

            property.setImageUrls(imageUrls);
        }

        var savedProperty = propertyRepository.save(property);

        return new PropertyDTO(savedProperty);

    }

    public void addPropertyOwnershipDocuments(String propertyId, List<MultipartFile> documents) {
        Optional<Property> propertyOptional = propertyRepository.findById(propertyId);

        if (propertyOptional.isPresent()) {
            Property property = propertyOptional.get();
            List<String> documentUrls = documents.stream()
                    .map(s3Service::uploadFile)
                    .toList();

            property.setOwnershipDocumentsUrls(documentUrls);

            propertyRepository.save(property);

        }
    }

    public Optional<Property> getProperty(String propertyId) {
        return propertyRepository.findById(propertyId);
    }


    public List<PropertyDTO> findPropertyByCriteriaSearch(@Nullable Integer bedrooms,
                                                       @Nullable Integer bathrooms,
                                                       @Nullable Double minPrice,
                                                       @Nullable Double maxPrice,
                                                       @Nullable PropertyType propertyType,
                                                       @Nullable Density density,
                                                       @Nullable String location,
                                                       @Nullable City city) {
        List<Property> properties = propertyRepository.findBySearchCriteria(bedrooms,
                bathrooms,
                propertyType,
                city,
                density,
                location,
                minPrice,
                maxPrice);

        return properties.stream()
                .map(PropertyDTO::new)
                .toList();
    }


    public List<PropertyDTO> getPropertiesByIds(List<String> propertyIds) {
        var properties = propertyRepository.findAllById(propertyIds);

        return properties.stream()
                .map(PropertyDTO::new)
                .toList();
    }

    public Long countProperties() {
        return propertyRepository.count();
    }
}
