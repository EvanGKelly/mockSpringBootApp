package com.weather.mock;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor //  reduces boiler plate code e.g constructor
@RequestMapping("api/v1/sensors")
public class SensorController {

    private final SensorService sensorService;

    public SensorRepository repository;

    @GetMapping("/allSensors")
    public List<Sensor> fetchAllSensors(){
        return sensorService.getAllSensors();
    }

    @GetMapping("/sensorById")
    public Sensor fetchSensorById(@RequestParam int sensorNum){
        return sensorService.getSensorById(sensorNum);
    }

    @GetMapping("/sensorByDate")
    public String fetchSensorByDate(@RequestParam int sensorNum, @RequestParam(required = false) Integer date){
        return sensorService.getSensorByDate(sensorNum, date);
    }

    @PostMapping()
    public void registerNewSensor(@RequestBody Sensor sensor){
        sensorService.addNewSensor(sensor);
    }

    @PutMapping
    public void updateWeatherData(@RequestParam int sensorNum, @RequestBody WeatherData weatherData){
        System.out.println("updating sensor " + weatherData);
        sensorService.updateWeatherData(sensorNum, weatherData);
    }
}
