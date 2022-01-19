package com.emir.weathertest2.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class WeatherForecastMainDayDto {

    private String dt_txt;
    private WeatherForecastDto main;

}
