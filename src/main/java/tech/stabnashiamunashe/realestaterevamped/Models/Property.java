package tech.stabnashiamunashe.realestaterevamped.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "property")
@TypeAlias("Property")
@ToString
public class Property {

    @Id
    private String id;

    private String area;

    private String address;

    private Integer bedrooms;

    private Integer bathrooms;

    private String description;

    private Double price;

    @Embedded
    private PropertyFeatures propertyFeatures;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long visits;

    private Boolean active;

    @Embedded
    private Coordinates coordinates;

    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;

    @Enumerated(EnumType.STRING)
    private PropertyCondition propertyCondition;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime subscriptionExpiryDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dateCreated;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dateUpdated;

    @DBRef
    private Location location;

    private List<String> imageUrls;

    @DBRef
    private List<String> ownershipDocumentsUrls;

    @DBRef(lazy = true)
    private List<Comment> comments;

    @DBRef
    private PropertyOwner propertyOwner;
}
