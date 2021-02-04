package com.broadcom.assignment.weatherforecast.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.broadcom.assignment.weatherforecast.dto.ForecastDto;
import com.broadcom.assignment.weatherforecast.service.WeatherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class WeatherController {

@Autowired
private WeatherService weatherService;

@GetMapping(path="/getWeatherForecast")
public ResponseEntity getWeatherForecast(@RequestParam String latitude,@RequestParam String longitude) 
{
	 List<ForecastDto> foreCastListForNxt5Days=null; 
	try {
		foreCastListForNxt5Days=weatherService.getWeatherForecast(latitude,longitude);
	  }
	 catch (Exception e) {
		 return new ResponseEntity("Unable to provide data for requested point,Please verify again",HttpStatus.BAD_REQUEST); 
	}	 
	 if(!ObjectUtils.isEmpty(foreCastListForNxt5Days))
			return new ResponseEntity<List<?>>(foreCastListForNxt5Days,HttpStatus.OK);
	 else
		 return new ResponseEntity("Sorry,Couldnt process the request,Please try later",HttpStatus.BAD_REQUEST); 
   }

}
