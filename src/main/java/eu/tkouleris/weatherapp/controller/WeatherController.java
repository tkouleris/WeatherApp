package eu.tkouleris.weatherapp.controller;

import com.google.gson.Gson;
import eu.tkouleris.weatherapp.dto.response.ResponseDTO;
import eu.tkouleris.weatherapp.entity.City;
import eu.tkouleris.weatherapp.entity.User;
import eu.tkouleris.weatherapp.repository.CityRepository;
import eu.tkouleris.weatherapp.repository.UserRepository;
import eu.tkouleris.weatherapp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    Gson gsonObj;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    WeatherService weatherService;

    @GetMapping(path = "forecast/{city_id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getWeather(@PathVariable("city_id") long city_id) {
        City city = cityRepository.findById(city_id);
        ResponseDTO myWeatherDTO = weatherService.getFiveDaysForecast(city.getOwm_id());
        return new ResponseEntity<Object>(gsonObj.toJson(myWeatherDTO), HttpStatus.OK);
    }

    @GetMapping(path = "/user/forecasts", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUserWeather(Authentication authentication) {
        User LoggedInUser = userRepository.findByUsername(authentication.getName());
        List<City> cities = cityRepository.findByUserId(LoggedInUser.getId());
        List<ResponseDTO> weathers = new ArrayList<>();
        for (City city : cities){
            ResponseDTO rdto = weatherService.getFiveDaysForecast(city.getOwm_id());
            weathers.add(rdto);
        }
        return new ResponseEntity<Object>(weathers, HttpStatus.OK);
    }
}
