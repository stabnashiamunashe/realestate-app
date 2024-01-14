package tech.stabnashiamunashe.realestaterevamped.Repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import tech.stabnashiamunashe.realestaterevamped.Models.City;
import tech.stabnashiamunashe.realestaterevamped.Models.Density;
import tech.stabnashiamunashe.realestaterevamped.Models.Property;
import tech.stabnashiamunashe.realestaterevamped.Models.PropertyType;

import java.util.List;

public interface PropertyRepository extends MongoRepository<Property, String> {
    @Query("{"
            + "?#{ ( #bedrooms != null ) ? ( 'bedrooms' : null ) }"
            + "?#{ ( #bathrooms != null ) ? ( ', bathrooms : ' + #bathrooms ) : '' }"
            + "?#{ ( #propertyType != null ) ? ( ', propertyType : ' + #propertyType ) : '' }"
            + "?#{ ( #city != null ) ? ( ', city : ' + #city ) : '' }"
            + "?#{ ( #density != null ) ? ( ', location.density : ' + #density ) : '' }"
            + "?#{ ( #location != null ) ? ( ', location.name : ' + #location ) : '' }"
            + "?#{ ( #minPrice != null && #maxPrice != null ) ? ( ', price : { $gte : ' + #minPrice + ', $lte : ' + #maxPrice + ' }' ) : "
            + "?#{ ( #minPrice != null ) ? ( ', price : { $gte : ' + #minPrice + ' }' ) : '' }"
            + "?#{ ( #maxPrice != null ) ? ( ', price : { $lte : ' + #maxPrice + ' }' ) : '' }"
            + "}")
    List<Property> findBySearchCriteria(Integer bedrooms,
                                        Integer bathrooms,
                                        PropertyType propertyType,
                                        City city,
                                        Density density,
                                        String location,
                                        Double minPrice,
                                        Double maxPrice);

    List<Property> findByPropertyOwner_Email(String email);
}
