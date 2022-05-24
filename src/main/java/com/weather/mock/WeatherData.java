package com.weather.mock;

import lombok.Data;

import java.time.LocalDate;

@Data
public class WeatherData {

    private int humidity;
    private int temperature;
    private LocalDate date;

    public WeatherData(int humidity, int temperature) {
        this.humidity = humidity;
        this.temperature = temperature;
        this.date = LocalDate.now();
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
