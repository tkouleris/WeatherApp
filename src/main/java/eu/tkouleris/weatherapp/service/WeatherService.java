package eu.tkouleris.weatherapp.service;

import com.google.gson.Gson;
import eu.tkouleris.weatherapp.dto.response.ResponseDTO;
import eu.tkouleris.weatherapp.dto.response.ResponseWeatherDTO;
import eu.tkouleris.weatherapp.dto.owm.OWMResponse;
import eu.tkouleris.weatherapp.dto.owm.OWMResponseSample;
import eu.tkouleris.weatherapp.service.contract.IWeatherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService implements IWeatherService {

    Gson gsonObj;

    public WeatherService(Gson gsonObj){
        this.gsonObj = gsonObj;
    }

    @Value("${owm_key}")
    private String owm_key;

    public ResponseDTO getFiveDaysForecast(long owm_id){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.openweathermap.org/data/2.5/forecast?id="+owm_id+"&appid="+this.owm_key+"&units=metric";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);//or any other required
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class
        );


        OWMResponse result = gsonObj.fromJson(response.getBody(), OWMResponse.class);
        ResponseDTO myWeatherDTO = new ResponseDTO();
        myWeatherDTO.setCity_id(result.getCity().getId());
        myWeatherDTO.setCity_name(result.getCity().getName());
        myWeatherDTO.setCountry(result.getCity().getCountry());
        List<ResponseWeatherDTO> weatherList = new ArrayList<>();
        for (OWMResponseSample sample: result.getList()) {
            ResponseWeatherDTO weatherDTO = new ResponseWeatherDTO();
            weatherDTO.setTimestamp(sample.getDt());
            weatherDTO.setTemperature(sample.getMain().getTemp());
            weatherDTO.setHumidity(sample.getMain().getHumidity());
            weatherDTO.setWeather_description(sample.getWeather().get(0).getDescription());
            weatherDTO.setIcon("http://openweathermap.org/img/wn/" + sample.getWeather().get(0).getIcon() + "@2x.png");
            weatherList.add(weatherDTO);
        }
        myWeatherDTO.setWeather(weatherList);

        return myWeatherDTO;
    }

}
