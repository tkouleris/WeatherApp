package eu.tkouleris.weatherapp.service.contract;

import eu.tkouleris.weatherapp.dto.response.ResponseDTO;

public interface IWeatherService {
    ResponseDTO getFiveDaysForecast(long id);
}
