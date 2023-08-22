package com.oceangromits.firmware.controller.api;

import com.oceangromits.firmware.weatherModel.Weather;
import com.oceangromits.firmware.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/weather")
public class WeatherController {
    WeatherRepository weatherRepo;


    @RequestMapping("/addNewWeather")
    public void addNewWeather() { //careful I think it might need to change so parameters can be input
        Weather newWeather = new Weather();
        weatherRepo.save(newWeather);
    }


    @RequestMapping("/getAllWeather")
    public String getAllWeather() {
        String response = "";
        Iterable<Weather> weathers = weatherRepo.findAll();
        for(Weather weather: weathers) {
            response += weather.getId() + "" + weather.getTime() + "" + weather.getTemperature() + "\n";
        }
        return response;
    }




}
