package com.weather.mock;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

// This class contains all business logic for the application
// Separate from the Controller
@AllArgsConstructor
@Service
public class SensorService {

    private final SensorRepository sensorRepository;

    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }

    public Sensor getSensorById(int sensorNum){
        return sensorRepository.findSensorBySensorNum(sensorNum).orElseThrow(() -> new IllegalStateException(
                "Sensor not Found: Invalid Sensor Number"));
    }

    public void addNewSensor(Sensor sensor) {
        Optional<Sensor> sensorById = sensorRepository.findSensorBySensorNum(sensor.getSensorNum());

        if(sensorById.isPresent()) {
            throw new IllegalStateException("Sensor Already Exists");
        }
        sensorRepository.save(sensor);
    }

    public void updateWeatherData(int sensorNum, WeatherData weatherData) {
        Sensor sensor = sensorRepository.findSensorBySensorNum(sensorNum).orElseThrow(() -> new IllegalStateException(
                "Sensor not Found: Invalid Sensor Number"));

        sensor.addWeatherData(weatherData);

        sensorRepository.save(sensor);
    }

    public String getSensorByDate(int sensorNum, Integer days) {
        int avgTemp = 0;
        int avgHumidity = 0;
        Sensor sensor = sensorRepository.findSensorBySensorNum(sensorNum).orElseThrow(() -> new IllegalStateException(
                "Sensor not Found: Invalid Sensor Number"));

        LocalDate now = LocalDate.now();
        String output = "Sensor " + sensorNum + " has the following Data in the last " + days + ":";

        for(int i =0; i < sensor.getWeatherDataList().size(); i++)
        {
            Duration duration = Duration.between(now.atStartOfDay(), sensor.getWeatherDataList().get(i).getDate().atStartOfDay());
            long diff = Math.abs(duration.toDays());

            if(days == null){
                System.out.println("Days not specified");
                output += ("\n" + "Latest Data is from is: \n"
                        + sensor.getWeatherDataList().get(sensor.getWeatherDataList().size() -1));
                avgTemp += sensor.getWeatherDataList().get(sensor.getWeatherDataList().size() -1).getTemperature();
                avgHumidity += sensor.getWeatherDataList().get(sensor.getWeatherDataList().size() -1).getHumidity();
                break;
            }
            else{
                avgTemp += sensor.getWeatherDataList().get(i).getTemperature();
                avgHumidity += sensor.getWeatherDataList().get(i).getHumidity();

                if(days != null && days >= diff) {
                    output += ("\n" + diff + " day(s) ago " + " Data was: \n"
                            + sensor.getWeatherDataList().get(i));
                }
            }
        }
        if(days != null){
            avgTemp = avgTemp/sensor.getWeatherDataList().size();
            avgHumidity = avgHumidity/sensor.getWeatherDataList().size();
        }

        output += "\nAverage Temperature: " + avgTemp;
        output += "\nAverage Humidity: " + avgHumidity;
        return output;
    }
}
