package eu.tkouleris.weatherapp.repository;

import eu.tkouleris.weatherapp.entity.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CityRepository extends CrudRepository<City, Long> {
    City findById(long id);
    @Query( value="SELECT * FROM city LEFT JOIN user_city ON (user_city.city_id = city.id) WHERE user_city.user_id = ?1",
            nativeQuery = true)
    List<City> findByUserId(long userId);
}
