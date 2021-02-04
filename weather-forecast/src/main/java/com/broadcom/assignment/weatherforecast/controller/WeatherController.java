package com.broadcom.assignment.weatherforecast.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.broadcom.assignment.weatherforecast.service.WeatherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class WeatherController {

@Autowired
private WeatherService weatherService;

@GetMapping(path="/getWeatherForecast")
public ResponseEntity<List<?>> getWeatherForecast(@RequestParam String latitude,@RequestParam String longitude) throws JsonMappingException, JsonProcessingException
{
	return new ResponseEntity<List<?>>(weatherService.getWeatherForecast(latitude,longitude),HttpStatus.OK);
}

}
