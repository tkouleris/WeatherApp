package eu.tkouleris.weatherapp.service;

import eu.tkouleris.weatherapp.dto.response.CountryDto;
import eu.tkouleris.weatherapp.entity.City;
import eu.tkouleris.weatherapp.exception.EntityExistsException;
import eu.tkouleris.weatherapp.exception.EntityNotFoundException;
import eu.tkouleris.weatherapp.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CityService {

    CityRepository cityRepository;

    public CityService(CityRepository cityRepository){
        this.cityRepository = cityRepository;
    }

    public void subscribeCityToUser(long user_id, long city_id) throws EntityNotFoundException, EntityExistsException {
        City city = cityRepository.findById(city_id);
        if(city == null){
            throw new EntityNotFoundException("City not found");
        }
        City existingRecord = cityRepository.findByUserIdAndByCityId(user_id,city_id);
        if(existingRecord != null){
            throw new EntityExistsException("User already subscribed to this city!");
        }
        cityRepository.addCityToUser(city.getId(), user_id);
    }

    public void unsubscribeCityFromUser(long user_id, long city_id) throws EntityNotFoundException {
        City existingRecord = cityRepository.findByUserIdAndByCityId(user_id,city_id);
        if(existingRecord == null){
            throw new EntityNotFoundException("User not subscribed to this city");
        }
        cityRepository.deleteCityFromUser(city_id, user_id);
    }

    public List<CountryDto> getAllCountries(){
        List<CountryDto> countries = new ArrayList<>();
        List<City> cities = cityRepository.getCountries(Sort.by(Sort.Direction.ASC, "country"));
        for (City city: cities) {
            Locale l = new Locale("", city.getCountry());
            CountryDto c = new CountryDto();
            c.setFullName(l.getDisplayCountry());
            c.setAbbreviation(city.getCountry());
            countries.add(c);
        }
        return countries;
    }

    public List<City> getFilteredCities(String country, String filter){
        return cityRepository.getCitiesByCountry(country, filter);
    }
}
