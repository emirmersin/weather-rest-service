package com.emir.weathertest2.service;


import com.emir.weathertest2.dto.WeatherCurrent;
import com.emir.weathertest2.dto.WeatherCurrentRequestDto;
import com.emir.weathertest2.entity.WeatherEntity;
import com.emir.weathertest2.repository.WeatherRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class WeatherService implements IWeatherService{


    private final RestTemplate restTemplate;

    private final WeatherRepository repository;

    @Override
    public List<WeatherEntity> findAll() {
        return repository.findAll();
    }


    public WeatherService(RestTemplate restTemplate, WeatherRepository repository) {
        this.restTemplate = restTemplate;
        this.repository = repository;
    }

    public List<WeatherCurrent> getWeather(WeatherCurrentRequestDto request) throws ParseException {
        List<WeatherCurrent> weatherCurrentList = new ArrayList<>();
        for (String city:request.getCityList()) {
              String url = "https://api.openweathermap.org/data/2.5/forecast?q="+city+"&appid=c9b8dd30b8783ec85c8af00b6d47d2ef";
              WeatherCurrent weatherCurrent = restTemplate.getForObject(url,WeatherCurrent.class);
              convertCelsius(weatherCurrent);
              weatherCurrentList.add(weatherCurrent);
        }
        return weatherCurrentList;
    }

    public WeatherCurrent convertCelsius(WeatherCurrent weatherCurrent) throws ParseException {
        for(int i = 0; i < weatherCurrent.getList().size(); i++){
                weatherCurrent.getList().get(i).getMain().setTemp(weatherCurrent.getList().get(i).getMain().getTemp() - 273);
                WeatherEntity weatherEntity = new WeatherEntity();
                weatherEntity.setCity(weatherCurrent.getCity().getName());
                weatherEntity.setTemp(weatherCurrent.getList().get(i).getMain().getTemp());
                weatherEntity.setHumidity(weatherCurrent.getList().get(i).getMain().getHumidity());
                Date date = stringToDate(weatherCurrent.getList().get(i).getDt_txt());
                weatherEntity.setDate(date);
                repository.saveAndFlush(weatherEntity);
        }
        return weatherCurrent;
    }


    public Date stringToDate(String date) throws ParseException {
        SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date result = sdt.parse(date);
        return result;
    }

    public List<WeatherEntity> sortDate(){
        return repository.findAll(Sort.by(Sort.Direction.ASC, "date"));
    }

    public List<WeatherEntity> sortName(){
        return repository.findAll(Sort.by(Sort.Direction.ASC, "city"));
    }


}
