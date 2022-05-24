# Mock Spring Boot App

### Rest API application using Spring Boot and MongoDB

# Requirements
***

### To run this application you wil need
* Java 11.0
* Mongo (I use version 5.0.8)
* Maven 3.8.5
* Docker (I use version 20.10.11)
* Postman


# Usage
***

1. Run MongoDB and MongoExpress with Docker
```shell
/usr/local/bin/docker-compose -f <path to following file>/docker-compose.yaml up -d

```
Alternatively you could run the containers by using the IntelliJ UI.
***<img width="497" alt="Screenshot 2022-05-24 at 19 16 36" src="https://user-images.githubusercontent.com/47178446/170112766-7b132771-d5ad-4f25-ad26-98b217d51d17.png">

2. Then run the main method in ```MockApplication.java```
3. From here you are now ready to make requests to the RestAPI

# Runnning the Http Requests with Postman



* Get All Sensors: 
  1. Select `Get` Option
  2. Input following url ```http://localhost:8080/api/v1/sensors/allSensors```
  3. Click Send 
  4. Returns a JSON string of all Sensors and their data

* Get Sensor By Id:
  1. Select `Get` Option
  2. Input following url ```http://localhost:8080/api/v1/sensors/sensorById?sensorNum=2```
  3. Change `sensorNum` in previous step to request other sensors
  4. Click Send
  5. Returns a JSON string of a specific Sensor and its data
  
* Get Sensor By Date:
  1. Select `Get` Option
  2. Input following url ```http://localhost:8080/api/v1/sensors/sensorByDate?sensorNum=1&date=7```
  3. Parameter `date` is how many days old the sensors that return can be. e.g. 7 days old
  4. Returns a String which contains all Weather Data relevant to the sensor and the date along woth Average temp and humidity of that sensor
  5. If date is not included just returns the most recent data.
  
* Create `New` Senor:
  1. Select `Post` Option
  2. Input following url ```http://localhost:8080/api/v1/sensors```
  3. In the body of request include:
  ```json
  {
      "sensorNum": 3,
      "country": "Ireland",
      "city": "Limerick",
      "weatherDataList": [
       {
          "humidity": 45,
          "temperature": 30
       }
      ]
  }
    ```
  4. Previous json string can be changed as you wish.
  5. If this sensor is already present Exception will throw

* Update Weather Data of Existing Sensor
  1. Select `Put` Option
  2. Input following url `http://localhost:8080/api/v1/sensors?sensorNum=3`
  3. Parameter sensorNum can be changed to any existing sensor numbers in the database
  4. In the Body of the request include:
  ```json 
    {
    "humidity": 78,
    "temperature": 110
    }
  ```
  5. This will update the selected Sensor with this weather data

# Tests
***

### To run the tests run `mvn clean test`
* GetAllSensorsTest
* GetSensorByIdTest
* GetSensorByDateTest
  
