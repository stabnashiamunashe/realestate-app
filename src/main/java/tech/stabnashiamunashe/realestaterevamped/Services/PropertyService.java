package tech.stabnashiamunashe.realestaterevamped.Services;

import jakarta.annotation.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tech.stabnashiamunashe.realestaterevamped.City;
import tech.stabnashiamunashe.realestaterevamped.DTOs.PropertyDTO;
import tech.stabnashiamunashe.realestaterevamped.Density;
import tech.stabnashiamunashe.realestaterevamped.Property;
import tech.stabnashiamunashe.realestaterevamped.PropertyType;
import tech.stabnashiamunashe.realestaterevamped.Repos.PropertyRepository;

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

        if(images != null) {
            List<String> imageUrls = images.stream()
                    .map(s3Service::uploadFile)
                    .toList();

            property.setImageUrls(imageUrls);
        }

        var savedProperty = propertyRepository.save(property);

        return new PropertyDTO(savedProperty);

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
}
