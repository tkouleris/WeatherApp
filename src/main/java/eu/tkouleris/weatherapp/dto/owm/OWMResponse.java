package eu.tkouleris.weatherapp.dto.owm;

import java.util.List;

public class OWMResponse {
    private OWMResponseCity city;
    private List<OWMResponseSample> list;

    public List<OWMResponseSample> getList() {
        return list;
    }

    public void setList(List<OWMResponseSample> list) {
        this.list = list;
    }

    public OWMResponseCity getCity() {
        return city;
    }

    public void setCity(OWMResponseCity city) {
        this.city = city;
    }
}
