package eu.tkouleris.weatherapp.controller;

import eu.tkouleris.weatherapp.dto.response.ResponseDTO;
import eu.tkouleris.weatherapp.entity.City;
import eu.tkouleris.weatherapp.entity.User;
import eu.tkouleris.weatherapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/weather")
public class CityController {
    @Autowired
    UserRepository userRepository;

    @PostMapping(path = "user/city/{city_id}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> setCityToUser(@PathVariable("city_id") long city_id, Authentication authentication) {
        User LoggedInUser = userRepository.findByUsername(authentication.getName());
        System.out.println("City ID:" + city_id);
        System.out.println("User ID:" + LoggedInUser.getId());

        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }
}
