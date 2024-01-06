package tech.stabnashiamunashe.realestaterevamped.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import tech.stabnashiamunashe.realestaterevamped.Security.Models.User;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
@TypeAlias("Tenant")
public class Tenant extends User {

    private List<String> previousAddresses;
    private List<String> rentalReferences;

}
