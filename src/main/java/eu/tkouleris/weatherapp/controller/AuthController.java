package eu.tkouleris.weatherapp.controller;

import eu.tkouleris.weatherapp.dto.response.ApiResponse;
import eu.tkouleris.weatherapp.dto.response.LoginDto;
import eu.tkouleris.weatherapp.entity.User;
import eu.tkouleris.weatherapp.repository.UserRepository;
import eu.tkouleris.weatherapp.service.CustomUserDetailsService;
import eu.tkouleris.weatherapp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class AuthController {
//    @Autowired
//    private UserCrudService userCrudService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private ApiResponse apiResponse;

    @Autowired
    private LoginDto loginDto;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/authenticate", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> authenticateUser(@RequestBody User user){

        Authentication auth = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        authenticationManager.authenticate(auth);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        User LoggedInUser = userRepository.findByUsername(user.getUsername());

        loginDto.setJwt(jwt);
        loginDto.setUsername(user.getUsername());
        loginDto.setUserid(LoggedInUser.getId());

        apiResponse.setMessage("Auth Token!");
        apiResponse.setData(loginDto);

        return new ResponseEntity<>(apiResponse.getBodyResponse(), HttpStatus.OK);
    }
}
