package eu.tkouleris.weatherapp.controller;

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
//    @Autowired
//    private RestTemplate restTemplate;

    @GetMapping(path = "forecast")
    public ResponseEntity<Object> getWeather() {
        System.out.println("HERE");
//        https://www.youtube.com/watch?v=B792AiYpb50
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://api.openweathermap.org/data/2.5/forecast?id=524901&&appid=93265401429e6e79657b6e0b6d6acb96";
//        Object[] customerJson = restTemplate.getForObject(url, Object[].class);
//        System.out.println(customerJson.toString());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);//or any other required
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class
        );
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }
}
