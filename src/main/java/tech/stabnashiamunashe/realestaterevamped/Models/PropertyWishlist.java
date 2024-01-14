package tech.stabnashiamunashe.realestaterevamped.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "property_wish_list")
public class PropertyWishlist {

    @Id
    private String id;

    private String propertyId;

    private String tenantId;

    public PropertyWishlist(String propertyId, String userId) {
    }
}