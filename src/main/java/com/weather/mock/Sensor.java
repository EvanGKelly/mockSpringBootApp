package com.weather.mock;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Sensor {
    @Id
    private String id;
    @Indexed(unique = true)
    private int sensorNum;
    private String country;
    private String city;
    private List<WeatherData> weatherDataList;

    public Sensor(int sensorNum, String country, String city, List<WeatherData> weatherDataList) {
        this.sensorNum = sensorNum;
        this.country = country;
        this.city = city;
        this.weatherDataList = weatherDataList;
    }

    public void addWeatherData(WeatherData weatherData){
        weatherDataList.add(weatherData);
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSensorNum() {
        return sensorNum;
    }

    public void setSensorNum(int sensorNum) {
        this.sensorNum = sensorNum;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<WeatherData> getWeatherDataList() {
        return weatherDataList;
    }

    public void setWeatherDataList(List<WeatherData> weatherDataList) {
        this.weatherDataList = weatherDataList;
    }
}
