package com.example.weatherapplication.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String city;

    public City(String city) {
        this.city = city;
    }

    public City() {
    }

    public String getCity() {
        return city;
    }
}
