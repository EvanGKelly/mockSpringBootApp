package com.weather.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SensorController.class)
public class SensorServiceTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	ObjectMapper mapper;

	@MockBean
	SensorRepository sensorRepository;

	@MockBean
	SensorService sensorService;

	static WeatherData wd;
	static List<WeatherData> wdList;
	static Sensor sensor1;
	static Sensor sensor2;
	static Sensor sensor3;


	@BeforeAll
	 public static void init(){
		wd = new WeatherData(25, 21);

		wdList = new ArrayList<>();

		wdList.add(wd);

		sensor1 =  new Sensor(1,"Ireland", "Galway", wdList);
		sensor2 =  new Sensor(2,"Ireland", "Dublin", wdList);
		sensor3 =  new Sensor(3,"Ireland", "Limerick", wdList);
	}

	@Test
	void GetAllSensorsTest() throws Exception {
		List<Sensor> sensors = new ArrayList<>();
		sensors.add(sensor1);
		sensors.add(sensor2);
		sensors.add(sensor3);

		final String expectedResponse = mapper.writeValueAsString(sensors);

		Mockito.when(sensorService.getAllSensors()).thenReturn(sensors);
		this.mockMvc.perform(get("/api/v1/sensors/allSensors"))
				.andExpect(status().isOk())
				.andExpect(content().json(expectedResponse));
	}

	@Test
	void GetSensorById() throws Exception {
		List<Sensor> sensors = new ArrayList<>();
		sensors.add(sensor1);
		sensors.add(sensor2);
		sensors.add(sensor3);

		final String expectedResponse = mapper.writeValueAsString(sensors.get(0));

		Mockito.when(sensorService.getSensorById(1)).thenReturn(sensor1);

		this.mockMvc.perform(get("/api/v1/sensors/sensorById")
						.param("sensorNum", String.valueOf(1)))
				.andExpect(status().isOk())
				.andExpect(content().json(expectedResponse));
	}

	@Test
	void getSensorByDate() throws Exception {
		List<Sensor> sensors = new ArrayList<>();
		sensors.add(sensor1);
		sensors.add(sensor2);
		sensors.add(sensor3);

		final String expectedResponse = "Sensor 1 has the following Data in the last 7:\n" +
				"1 day(s) ago  Data was: \n" +
				"WeatherData(humidity=25, temperature=19, date=2022-05-23)\n" +
				"1 day(s) ago  Data was: \n" +
				"WeatherData(humidity=100, temperature=40, date=2022-05-23)";

		String output = "Sensor 1 has the following Data in the last 7:\n" +
				"1 day(s) ago  Data was: \n" +
				"WeatherData(humidity=25, temperature=19, date=2022-05-23)\n" +
				"1 day(s) ago  Data was: \n" +
				"WeatherData(humidity=100, temperature=40, date=2022-05-23)";

		Mockito.when(sensorService.getSensorByDate(1, 7)).thenReturn(output);

		this.mockMvc.perform(get("/api/v1/sensors/sensorByDate")
						.param("sensorNum", String.valueOf(1))
						.param("date", String.valueOf(7)))
				.andExpect(status().isOk())
				.andExpect(content().string(expectedResponse));
	}
}