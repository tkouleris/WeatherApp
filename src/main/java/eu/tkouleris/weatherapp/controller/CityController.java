package eu.tkouleris.weatherapp.controller;

import eu.tkouleris.weatherapp.dto.response.ResponseDTO;
import eu.tkouleris.weatherapp.entity.City;
import eu.tkouleris.weatherapp.entity.User;
import eu.tkouleris.weatherapp.exception.EntityExistsException;
import eu.tkouleris.weatherapp.exception.EntityNotFoundException;
import eu.tkouleris.weatherapp.repository.CityRepository;
import eu.tkouleris.weatherapp.repository.UserRepository;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/weather")
public class CityController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CityRepository cityRepository;

    @PostMapping(path = "user/city/{city_id}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> setCityToUser(@PathVariable("city_id") long city_id, Authentication authentication) throws Exception {
        User LoggedInUser = userRepository.findByUsername(authentication.getName());
        City city = cityRepository.findById(city_id);
        if(city == null){
            throw new EntityNotFoundException("City not found");
        }
        City existingRecord = cityRepository.findByUserIdAndByCityId(LoggedInUser.getId(),city_id);
        if(existingRecord != null){
            throw new EntityExistsException("City is already combined with user");
        }
        cityRepository.addCityToUser(city.getId(), LoggedInUser.getId());

        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }

    @DeleteMapping(path = "user/city/{city_id}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> removeCityFromUser(@PathVariable("city_id") long city_id, Authentication authentication) throws Exception {
        User LoggedInUser = userRepository.findByUsername(authentication.getName());
        City existingRecord = cityRepository.findByUserIdAndByCityId(LoggedInUser.getId(),city_id);
        if(existingRecord == null){
            throw new EntityNotFoundException("City not combined with user");
        }
        cityRepository.deleteCityFromUser(city_id, LoggedInUser.getId());

        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }
}
