package tech.stabnashiamunashe.realestaterevamped;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import tech.stabnashiamunashe.realestaterevamped.Security.Models.User;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "property_owner")
public class PropertyOwner extends User {

    @DBRef
    private List<Property> properties;
}
