package eu.tkouleris.weatherapp.controller;

import com.google.gson.Gson;
import eu.tkouleris.weatherapp.jsonModel.OWMResult;
import eu.tkouleris.weatherapp.jsonModel.WeatherModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/weather/")
public class WeatherController {

    @GetMapping(path = "forecast", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getWeather() {

        RestTemplate restTemplate = new RestTemplate();

        String url = "https://api.openweathermap.org/data/2.5/forecast?id=524901&&appid=93265401429e6e79657b6e0b6d6acb96&units=metric";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);//or any other required
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class
        );

        Gson gson = new Gson();
        OWMResult result = gson.fromJson(response.getBody(), OWMResult.class);
        return new ResponseEntity<Object>(gson.toJson(result), HttpStatus.OK);
    }
}
