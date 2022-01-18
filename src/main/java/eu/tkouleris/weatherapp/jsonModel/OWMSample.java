package eu.tkouleris.weatherapp.jsonModel;

import java.util.List;
import java.util.Map;

public class OWMSample {
    long dt;
    MainOWMResult main;

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public MainOWMResult getMain() {
        return main;
    }

    public void setMain(MainOWMResult main) {
        this.main = main;
    }
}
