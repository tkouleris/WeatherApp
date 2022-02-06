package eu.tkouleris.weatherapp.dto.response;

import java.util.List;

public class ResponseDTO {
    private long city_id;
    private String city_name;
    private String country;
    private List<ResponseWeatherDTO> weather;

    public long getCity_id() {
        return city_id;
    }

    public void setCity_id(long city_id) {
        this.city_id = city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public List<ResponseWeatherDTO> getWeather() {
        return weather;
    }

    public void setWeather(List<ResponseWeatherDTO> weather) {
        this.weather = weather;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
