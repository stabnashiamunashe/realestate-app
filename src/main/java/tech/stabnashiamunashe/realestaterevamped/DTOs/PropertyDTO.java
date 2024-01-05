package tech.stabnashiamunashe.realestaterevamped.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import tech.stabnashiamunashe.realestaterevamped.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PropertyDTO {

    private String id;

    private String area;

    private String address;

    private Integer bedrooms;

    private Integer bathrooms;

    private String description;

    private Double price;

    private PropertyFeatures propertyFeatures;

    private Boolean active;

    private Coordinates coordinates;

    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;

    @Enumerated(EnumType.STRING)
    private PropertyCondition propertyCondition;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime subscriptionExpiryDate;

    private Location location;

    private List<String> imageUrls;

    private List<String> ownershipDocumentsUrls;

    private List<Comment> comments;

    private PropertyOwner propertyOwner;

    public PropertyDTO(Property property) {
        this.id = property.getId();
        this.area = property.getArea();
        this.address = property.getAddress();
        this.bedrooms = property.getBedrooms();
        this.bathrooms = property.getBathrooms();
        this.description = property.getDescription();
        this.price = property.getPrice();
        this.propertyFeatures = property.getPropertyFeatures();
        this.active = property.getActive();
        this.coordinates = property.getCoordinates();
        this.propertyType = property.getPropertyType();
        this.propertyCondition = property.getPropertyCondition();
        this.subscriptionExpiryDate = property.getSubscriptionExpiryDate();
        this.location = property.getLocation();
        this.imageUrls = property.getImageUrls();
        this.ownershipDocumentsUrls = property.getOwnershipDocumentsUrls();
        this.comments = property.getComments();
        this.propertyOwner = property.getPropertyOwner();
    }
}
