package eu.tkouleris.weatherapp.controller;

import eu.tkouleris.weatherapp.dto.response.CountryDto;
import eu.tkouleris.weatherapp.entity.City;
import eu.tkouleris.weatherapp.entity.User;
import eu.tkouleris.weatherapp.service.CityService;
import eu.tkouleris.weatherapp.service.UserCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class CityController {

    @Autowired
    UserCrudService userCrudService;

    @Autowired
    CityService cityService;

    @GetMapping(path = "/weather/countries", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllCountries(){
        List<CountryDto> countries = cityService.getAllCountries();
        return new ResponseEntity<Object>(countries, HttpStatus.OK);
    }

    @GetMapping(path = "/weather/city/{country}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getFilteredCountries(@PathVariable("country") String country, @RequestParam(required = false) String filtered){
        List<City> cities = cityService.getFilteredCities(country, filtered);
        return new ResponseEntity<Object>(cities, HttpStatus.OK);
    }


    @PostMapping(path = "/weather/city", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> setCityToUser(@RequestBody City city, Authentication authentication) throws Exception {
        User loggedInUser = this.userCrudService.findByUsername(authentication.getName());
        cityService.subscribeCityToUser(loggedInUser.getId(), city.getId());

        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }

    @PostMapping(path = "/weather/delete", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> removeCityFromUser(@RequestBody City city, Authentication authentication) throws Exception {
        User loggedInUser = this.userCrudService.findByUsername(authentication.getName());
        cityService.unsubscribeCityFromUser(loggedInUser.getId(), city.getId());
        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }
}
