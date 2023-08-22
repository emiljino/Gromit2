package com.oceangromits.firmware.repository;


import com.oceangromits.firmware.weatherModel.Weather;
import org.springframework.data.repository.CrudRepository;


public interface WeatherRepository extends CrudRepository<Weather, Long> {
} //Weather data is temporarily stored here while the application is using it