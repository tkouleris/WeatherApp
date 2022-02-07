package eu.tkouleris.weatherapp.service;

import eu.tkouleris.weatherapp.entity.City;
import eu.tkouleris.weatherapp.exception.EntityExistsException;
import eu.tkouleris.weatherapp.exception.EntityNotFoundException;
import eu.tkouleris.weatherapp.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    @Autowired
    CityRepository cityRepository;

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
}
