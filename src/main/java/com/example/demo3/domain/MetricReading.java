package com.example.demo3.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity

public class MetricReading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String sensorId;

    //Meta Data, country name, city name
    private String countryName;
    private String cityName;

    private String temperatureReading;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getTemperatureReading() {
        return temperatureReading;
    }

    public void setTemperatureReading(String temperatureReading) {
        this.temperatureReading = temperatureReading;
    }

}
