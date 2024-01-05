package tech.stabnashiamunashe.realestaterevamped.Services;

import org.springframework.stereotype.Service;
import tech.stabnashiamunashe.realestaterevamped.City;
import tech.stabnashiamunashe.realestaterevamped.Repos.CityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }


    public City createCity(City city) {

        if(cityRepository.existsByName(city.getName())) {
            return cityRepository.findByName(city.getName());
        }

        return cityRepository.save(city);
    }

    public void deleteCity(String id) {
        cityRepository.deleteById(id);
    }

    public City updateCity(City city) {
        City existingCity = cityRepository.findById(city.getId()).orElse(null);
        assert existingCity != null;
        existingCity.setName(city.getName());
        return cityRepository.save(existingCity);
    }

    public Optional<City> getCityById(String id) {
        return cityRepository.findById(id);
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }
}
