package com.weather.mock;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SensorRepository extends MongoRepository<Sensor, String> {

    Optional<Sensor> findSensorBySensorNum(int sensorNum);

}
