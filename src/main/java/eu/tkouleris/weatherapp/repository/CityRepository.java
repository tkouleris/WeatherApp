package eu.tkouleris.weatherapp.repository;

import eu.tkouleris.weatherapp.entity.City;
import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<City, Long> {
    City findById(long id);
}
