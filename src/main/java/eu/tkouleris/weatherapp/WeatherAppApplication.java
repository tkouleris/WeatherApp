package eu.tkouleris.weatherapp;

import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class WeatherAppApplication {

    public static void main(String[] args) {

        SpringApplication.run(WeatherAppApplication.class, args);
    }

    @Bean
    public Gson gsonObj(){
        return new Gson();
    }
//    @Bean
//    public RestTemplate getRestTemplate(){
//        return new RestTemplate();
//    }
//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder) {
//        return builder.build();
//    }
}
