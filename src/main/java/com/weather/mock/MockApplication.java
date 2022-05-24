package com.weather.mock;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@SpringBootApplication
public class MockApplication {

	public static void main(String[] args) {
		SpringApplication.run(MockApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(SensorRepository repository){
		return args -> {
			WeatherData wd = new WeatherData(25, 21);

			List<WeatherData> wdList = new ArrayList<>();

			wdList.add(wd);
			Sensor sensor = new Sensor(6,"Ireland", "Cavan", wdList);

			repository.findSensorBySensorNum(sensor.getSensorNum()).ifPresentOrElse(s -> {
				System.out.println("Already exists");
			}, () -> {
				System.out.println("Inserting " + sensor);
				repository.insert(sensor);
			});

//			Query query = new Query();
//			query.addCriteria(Criteria.where("sensorNum").is(sensor.getSensorNum()));
//
//			List<Sensor> sensors = mongoTemplate.find(query, Sensor.class);
//
//			if(sensors.size() > 1){
//				throw new IllegalStateException("Found existing doc");
//			}
//
//			if(sensors.isEmpty()){
//				System.out.println("Inserting the sensor " + sensor);
//				repository.insert(sensor);
//
//			}else{
//				System.out.println(sensor + "Already in db");
//			}

		};
	}

}
