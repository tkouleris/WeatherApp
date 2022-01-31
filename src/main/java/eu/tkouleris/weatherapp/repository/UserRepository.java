package eu.tkouleris.weatherapp.repository;

import eu.tkouleris.weatherapp.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String username);
    List<User> findByUsernameNot(String username);
}
