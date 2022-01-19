package com.emir.weathertest2.controller;


import com.emir.weathertest2.dto.WeatherCurrent;
import com.emir.weathertest2.dto.WeatherCurrentRequestDto;
import com.emir.weathertest2.entity.WeatherEntity;
import com.emir.weathertest2.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;


@RestController

public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @PostMapping(value = "/weather")
    public ResponseEntity<List<WeatherCurrent>> getWeather(@RequestBody WeatherCurrentRequestDto request) throws ParseException {
        request.getCityList().stream().forEach(System.out::println);
        return ResponseEntity.ok(weatherService.getWeather(request));
    }

    @GetMapping(value = "/weather/sort")
    public List<WeatherEntity> sortDate(){
        return weatherService.sortDate();
    }

    @GetMapping(value = "/weather/sortname")
    public List<WeatherEntity> sortName(){
        return weatherService.sortName();
    }

}