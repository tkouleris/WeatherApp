package eu.tkouleris.weatherapp.dto.owm;

import java.util.List;

public class OWMResponseSample {
    long dt;
    OWMResponseSampleMain main;
    List<OWMResponseSampleWeatherDescription> weather;

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public OWMResponseSampleMain getMain() {
        return main;
    }

    public void setMain(OWMResponseSampleMain main) {
        this.main = main;
    }

    public List<OWMResponseSampleWeatherDescription> getWeather() {
        return weather;
    }

    public void setWeather(List<OWMResponseSampleWeatherDescription> weather) {
        this.weather = weather;
    }
}
