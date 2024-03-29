package tech.stabnashiamunashe.realestaterevamped.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "city")
@TypeAlias("City")
public class City {

    private String id;

    private String name;

    private String country;
}
