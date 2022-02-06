package eu.tkouleris.weatherapp.controller;

import com.google.gson.Gson;
import eu.tkouleris.weatherapp.dto.response.ResponseDTO;
import eu.tkouleris.weatherapp.dto.response.ResponseWeatherDTO;
import eu.tkouleris.weatherapp.entity.City;
import eu.tkouleris.weatherapp.jsonModel.OWMResult;
import eu.tkouleris.weatherapp.jsonModel.OWMSample;
import eu.tkouleris.weatherapp.repository.CityRepository;
import eu.tkouleris.weatherapp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

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
    WeatherService weatherService;

    @GetMapping(path = "forecast/{city_id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getWeather(@PathVariable("city_id") long city_id) {
        City city = cityRepository.findById(city_id);
        ResponseDTO myWeatherDTO = weatherService.getFiveDaysForecast(city.getOwm_id());
        return new ResponseEntity<Object>(gsonObj.toJson(myWeatherDTO), HttpStatus.OK);
    }
}
