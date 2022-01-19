package com.emir.weathertest2.service;

import com.emir.weathertest2.entity.WeatherEntity;

import java.util.List;

public interface IWeatherService {

    List<WeatherEntity> findAll();
}
