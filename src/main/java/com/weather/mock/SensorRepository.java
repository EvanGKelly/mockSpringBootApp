package com.weather.mock;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

// Repository interface allows for various operations to be completed
public interface SensorRepository extends MongoRepository<Sensor, String> {
    Optional<Sensor> findSensorBySensorNum(int sensorNum);
}
