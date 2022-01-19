package com.emir.weathertest2.dto;

import java.util.List;

public class WeatherCurrentRequestDto {

    private List<String> cityList;

    public List<String> getCityList() {
        return cityList;
    }

    public void setCityList(List<String> cityList) {
        this.cityList = cityList;
    }
}
