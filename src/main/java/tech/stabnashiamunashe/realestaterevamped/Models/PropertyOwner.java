package tech.stabnashiamunashe.realestaterevamped.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import tech.stabnashiamunashe.realestaterevamped.Security.Models.User;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
@TypeAlias("PropertyOwner")
public class PropertyOwner extends User {

    @DBRef
    private List<Property> properties;
}
