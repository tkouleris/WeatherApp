package eu.tkouleris.weatherapp.jsonModel;

import java.util.List;

public class OWMResult {
//    private int cnt;
//    private int cod;
    private List<OWMSample> list;
//
//    public int getCod() {
//        return cod;
//    }
//
//    public void setCod(int cod) {
//        this.cod = cod;
//    }
//
//    public int getCnt() {
//        return cnt;
//    }
//
//    public void setCnt(int cnt) {
//        this.cnt = cnt;
//    }

    public List<OWMSample> getList() {
        return list;
    }

    public void setList(List<OWMSample> list) {
        this.list = list;
    }
}
