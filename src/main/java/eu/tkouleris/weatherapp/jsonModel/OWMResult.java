package eu.tkouleris.weatherapp.jsonModel;

import java.util.List;

public class OWMResult {
    private OWMCity city;
    private List<OWMSample> list;

    public List<OWMSample> getList() {
        return list;
    }

    public void setList(List<OWMSample> list) {
        this.list = list;
    }

    public OWMCity getCity() {
        return city;
    }

    public void setCity(OWMCity city) {
        this.city = city;
    }
}
