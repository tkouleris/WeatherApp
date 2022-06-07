package eu.tkouleris.weatherapp.controller;

import eu.tkouleris.weatherapp.dto.response.ApiResponse;
import eu.tkouleris.weatherapp.dto.response.ChangePasswordDto;
import eu.tkouleris.weatherapp.dto.response.LoginDto;
import eu.tkouleris.weatherapp.entity.User;
import eu.tkouleris.weatherapp.repository.UserRepository;
import eu.tkouleris.weatherapp.service.CustomUserDetailsService;
import eu.tkouleris.weatherapp.service.UserCrudService;
import eu.tkouleris.weatherapp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
public class AuthController {

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
    private UserCrudService userCrudService;

    @PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> register(@RequestBody User user) throws Exception{
        User newUser = userCrudService.createNewUser(user);
        apiResponse.setMessage("User created!");
        apiResponse.setData(newUser);

        return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.CREATED);
    }

    @PostMapping(path = "/password", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> changePassword(@RequestBody ChangePasswordDto changePassword, Authentication authentication) throws Exception{
        User loggedInUser = userCrudService.findByUsername(authentication.getName());
        loggedInUser.setPassword(changePassword.getPassword());
        User newUser = userCrudService.changePassword(loggedInUser);
        apiResponse.setMessage("Password Changed!");
        apiResponse.setData(newUser);

        return new ResponseEntity<>(apiResponse.getBodyResponse(),HttpStatus.CREATED);
    }


    @PostMapping(value = "/authenticate", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> authenticateUser(@RequestBody User user){

        Authentication auth = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        authenticationManager.authenticate(auth);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        User LoggedInUser = userCrudService.findByUsername(user.getUsername());

        loginDto.setJwt(jwt);
        loginDto.setUsername(user.getUsername());
        loginDto.setUserid(LoggedInUser.getId());

        apiResponse.setMessage("Auth Token!");
        apiResponse.setData(loginDto);

        return new ResponseEntity<>(apiResponse.getBodyResponse(), HttpStatus.OK);
    }
}
