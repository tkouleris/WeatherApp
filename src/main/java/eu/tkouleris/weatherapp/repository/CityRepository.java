package eu.tkouleris.weatherapp.repository;

import eu.tkouleris.weatherapp.entity.City;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface CityRepository extends CrudRepository<City, Long> {
    City findById(long id);
    @Query( value="SELECT * FROM city LEFT JOIN user_city ON (user_city.city_id = city.id) WHERE user_city.user_id = ?1",
            nativeQuery = true)
    List<City> findByUserId(long userId);

    @Query( value="SELECT * FROM city LEFT JOIN user_city ON (user_city.city_id = city.id) WHERE user_city.user_id = ?1 AND user_city.city_id = ?2",
            nativeQuery = true)
    City findByUserIdAndByCityId(long userId, long city_id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO user_city ( city_id, user_id ) VALUES(?1, ?2)", nativeQuery = true)
    void addCityToUser(long city_id, long user_id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM user_city WHERE city_id = ?1 AND user_id = ?2", nativeQuery = true)
    void deleteCityFromUser(long city_id, long user_id);
}
