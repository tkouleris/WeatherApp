package eu.tkouleris.weatherapp.service;

import com.google.gson.Gson;
import eu.tkouleris.weatherapp.dto.response.ResponseDTO;
import eu.tkouleris.weatherapp.dto.response.ResponseWeatherDTO;
import eu.tkouleris.weatherapp.jsonModel.OWMResult;
import eu.tkouleris.weatherapp.jsonModel.OWMSample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {

    @Autowired
    Gson gsonObj;

    public ResponseDTO getFiveDaysForecast(long owm_id){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.openweathermap.org/data/2.5/forecast?id="+owm_id+"&appid=93265401429e6e79657b6e0b6d6acb96&units=metric";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);//or any other required
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class
        );


        OWMResult result = gsonObj.fromJson(response.getBody(), OWMResult.class);
        ResponseDTO myWeatherDTO = new ResponseDTO();
        myWeatherDTO.setCity_id(result.getCity().getId());
        myWeatherDTO.setCity_name(result.getCity().getName());
        myWeatherDTO.setCountry(result.getCity().getCountry());
        List<ResponseWeatherDTO> weatherList = new ArrayList<>();
        for (OWMSample sample: result.getList()) {
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
