package com.emir.weathertest2.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;



@Getter
@Setter
@Entity
@Table(name= "Weather")
@RequiredArgsConstructor
public class WeatherEntity {

    @Id
    @GeneratedValue
    private int id;

    private String city;
    private int temp;
    private int humidity;
    private Date date;

    public WeatherEntity(int id, String city, int temp, int humidity, Date date) {
        super();
        this.id = id;
        this.city = city;
        this.temp = temp;
        this.humidity = humidity;
        this.date = date;
    }
}