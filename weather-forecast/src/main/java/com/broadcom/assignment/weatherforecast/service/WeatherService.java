package com.broadcom.assignment.weatherforecast.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.broadcom.assignment.weatherforecast.dto.ForecastDto;
import com.broadcom.assignment.weatherforecast.utils.RestClient;
import com.broadcom.assignment.weatherforecast.utils.WeatherConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WeatherService {

@Value("${weather.gov.points.url}")
private String pointsEndPoint;

@Autowired
private RestClient restClient;
	
public List<ForecastDto> getWeatherForecast(String latitude,String longitude) throws Exception
{
	String forecastEndPoint=null;
	 List<ForecastDto> foreCastDtoList=null; 
	 String weatherData=null;
	 List<ForecastDto> foreCastListForNxt5Days=null;
	 
	//Constructing the Points EndPoint using the given Geo co-ordinates
	String locPointUrl=pointsEndPoint+latitude+","+longitude;
	System.out.println("locationPointUrl:  {} "+locPointUrl);
	
	String pointApiResponse=restClient.invokeWeatherAPI(locPointUrl);
	System.out.println("pointApiResponse:  {} "+pointApiResponse);
	
	JSONObject pointJsonResponse=new JSONObject(pointApiResponse);
	
	//Deriving the ForeCast EndPoint from the Valid PointAPI Response
	if(!ObjectUtils.isEmpty(pointJsonResponse) )
	 forecastEndPoint=pointJsonResponse.getJSONObject(WeatherConstants.PROPS).getString(WeatherConstants.FORECAST);
	
	System.out.println("forecastEndPoint:  {} "+forecastEndPoint);

	String forecastDetails=restClient.invokeWeatherAPI(forecastEndPoint);
	JSONObject forecastData=new JSONObject(forecastDetails);
	
	//Deriving the WeatherData using the ForeCast API
	if(!ObjectUtils.isEmpty(forecastData))
	{
	 weatherData=forecastData.getJSONObject(WeatherConstants.PROPS).getJSONArray(WeatherConstants.PERIODS).toString();
 
	 ObjectMapper mapper = new ObjectMapper(); 
	foreCastDtoList = Arrays.asList(mapper.readValue(weatherData, ForecastDto[].class));
	//Consolidating the ForeCast Data for Next 5 days
	 foreCastListForNxt5Days=foreCastDtoList.stream().filter(f->f.getNumber()<=WeatherConstants.DAY_LIMIT).collect(Collectors.toList());
	}
	
	System.out.println("foreCastListForNxt5Days {}"+foreCastListForNxt5Days);
	
	return foreCastListForNxt5Days;
}



}
