package tech.stabnashiamunashe.realestaterevamped.Services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import tech.stabnashiamunashe.realestaterevamped.City;
import tech.stabnashiamunashe.realestaterevamped.Location;
import tech.stabnashiamunashe.realestaterevamped.Repos.CityRepository;
import tech.stabnashiamunashe.realestaterevamped.Repos.LocationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    private final CityRepository cityRepository;

    public LocationService(LocationRepository locationRepository, CityRepository cityRepository) {
        this.locationRepository = locationRepository;
        this.cityRepository = cityRepository;
    }

    public Location createLocation(Location location) {

        City city = cityRepository.findById(location.getCity().getId())
                .orElseThrow(() -> new EntityNotFoundException("City not found"));

        location.setCity(city);

        if(locationRepository.existsByName(location.getName())) {
            return locationRepository.findByName(location.getName());
        }
        return locationRepository.save(location);
    }

    public Optional<Location> getLocationById(String id) {
        return locationRepository.findById(id);
    }

    public Location updateLocation(Location location) {
        Location existingLocation = locationRepository.findById(location.getId()).orElse(null);
        assert existingLocation != null;
        existingLocation.setName(location.getName());
        return locationRepository.save(existingLocation);
    }

    public void deleteLocation(String id) {
        locationRepository.deleteById(id);
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }
}
