package eu.tkouleris.weatherapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    private String city_name;

    private long owm_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public long getOwm_id() {
        return owm_id;
    }

    public void setOwm_id(long owm_id) {
        this.owm_id = owm_id;
    }
}
