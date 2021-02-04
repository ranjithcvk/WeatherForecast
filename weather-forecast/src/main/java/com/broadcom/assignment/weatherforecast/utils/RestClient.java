package com.broadcom.assignment.weatherforecast.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClient {

private RestTemplate restTemplate;

@Autowired
public RestClient(RestTemplateBuilder builder){
    this.restTemplate= builder.build();
}

public String invokeWeatherAPI(String endPointUrl)
{
	return restTemplate.getForObject(endPointUrl,String.class);
}
	
}
