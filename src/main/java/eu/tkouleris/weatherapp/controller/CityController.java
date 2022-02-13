package eu.tkouleris.weatherapp.controller;

import eu.tkouleris.weatherapp.dto.response.CountryDto;
import eu.tkouleris.weatherapp.dto.response.ResponseDTO;
import eu.tkouleris.weatherapp.entity.City;
import eu.tkouleris.weatherapp.entity.User;
import eu.tkouleris.weatherapp.exception.EntityExistsException;
import eu.tkouleris.weatherapp.exception.EntityNotFoundException;
import eu.tkouleris.weatherapp.repository.CityRepository;
import eu.tkouleris.weatherapp.repository.UserRepository;
import eu.tkouleris.weatherapp.service.CityService;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/weather")
public class CityController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CityService cityService;

    @GetMapping(path = "city/countries", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllCountries(){
        List<CountryDto> countries = cityService.getAllCountries();
        return new ResponseEntity<Object>(countries, HttpStatus.OK);
    }

    @GetMapping(path = "city/{country}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getFilteredCountries(@PathVariable("country") String country){
        List<City> cities = cityService.getFilteredCities(country);
        return new ResponseEntity<Object>(cities, HttpStatus.OK);
    }

    @PostMapping(path = "user/city/{city_id}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> setCityToUser(@PathVariable("city_id") long city_id, Authentication authentication) throws Exception {
        User loggedInUser = userRepository.findByUsername(authentication.getName());
        cityService.subscribeCityToUser(loggedInUser.getId(), city_id);

        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }

    @DeleteMapping(path = "user/city/{city_id}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> removeCityFromUser(@PathVariable("city_id") long city_id, Authentication authentication) throws Exception {
        User loggedInUser = userRepository.findByUsername(authentication.getName());
        cityService.unsubscribeCityFromUser(loggedInUser.getId(), city_id);
        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }
}
