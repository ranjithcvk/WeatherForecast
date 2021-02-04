package com.broadcom.assignment.weatherforecast.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.broadcom.assignment.weatherforecast.WeatherForecastApplicationTests;
import com.broadcom.assignment.weatherforecast.service.WeatherService;

@RunWith(SpringJUnit4ClassRunner.class)
public class WeatherControllerTest extends WeatherForecastApplicationTests {
	
	private MockMvc mockMVC;
	
	@Mock
	private WeatherService weatherService;

	@InjectMocks
	private WeatherController weatherController;
	
	@Autowired
	private WebApplicationContext webAppContext;
	

	@BeforeEach
	void setUp() throws Exception {
		mockMVC=MockMvcBuilders.webAppContextSetup(webAppContext).build();
	}

	@Test
	void testGetWeatherForecast() throws Exception {
		LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("latitude", "39.7456");
		requestParams.add("longitude", "-97.0892");

		mockMVC.perform(
		MockMvcRequestBuilders.get("/getWeatherForecast").params(requestParams))
        .andExpect(status().isOk());
	}

}
