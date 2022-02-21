package eu.tkouleris.weatherapp.service;

import eu.tkouleris.weatherapp.entity.User;
import eu.tkouleris.weatherapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserCrudService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;


    public User createNewUser(User user) throws Exception {
        System.out.println("I AM HERE");
        if (user_exists(user)) throw new Exception("User already exists!");

        String userEmail = user.getEmail().trim();
        String userUsername = user.getUsername().trim();
        String userPassword = user.getPassword().trim();

        boolean usernameIsNotValid = (userUsername == null) || !userUsername.matches("[A-Za-z0-9_]+");
        /* Email Restriction
         * ---------------------
         *This expression matches email addresses, and checks that they are of the proper form.
         *It checks to ensure the top level domain is between 2 and 4 characters long,
         *but does not check the specific domain against a list (especially since
         *there are so many of them now).
         */
        boolean emailIsNotValid = (userEmail == null) || !userEmail.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

        /* Password Restriction
         * ------------------------
         * At least 8 chars
         * Contains at least one digit
         * Contains at least one lower alpha char and one upper alpha char
         * Contains at least one char within a set of special chars (@#%$^ etc.)
         * Does not contain space, tab, etc.
         */
        boolean passwordIsNotValid = (userPassword == null)
                || !userPassword.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}");

        if (usernameIsNotValid) throw new Exception("Username not set or not valid!");
        if (passwordIsNotValid) throw new Exception("Password not set or not valid");
        if (emailIsNotValid) throw new Exception("Email not set or not valid!");


        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User changePassword(User user) throws Exception {
        String userPassword = user.getPassword().trim();
        /* Password Restriction
         * ------------------------
         * At least 8 chars
         * Contains at least one digit
         * Contains at least one lower alpha char and one upper alpha char
         * Contains at least one char within a set of special chars (@#%$^ etc.)
         * Does not contain space, tab, etc.
         */
        boolean passwordIsNotValid = (userPassword == null)
                || !userPassword.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}");

        if (passwordIsNotValid) throw new Exception("Password not set or not valid");

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    private Boolean user_exists(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) return true;
        if (userRepository.findByEmail(user.getEmail()) != null) return true;
        return false;
    }
}
