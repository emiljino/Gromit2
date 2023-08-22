package com.oceangromits.firmware.weatherModel;

import javax.persistence.*;
import java.util.List;

@Entity
public class Weather { //weather class and the attributes needed with getters and setters

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String temperature;

    @Column(unique = true)
    private String time;

    public long getId() {
        return id;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getTime() {
        return time;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
