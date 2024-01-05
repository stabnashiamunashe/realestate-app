package tech.stabnashiamunashe.realestaterevamped.Models;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Document(collection = "location")
public class Location {

    @Id
    private String id;

    private City city;

    private String name;

    @Enumerated(EnumType.STRING)
    private Density density;

}