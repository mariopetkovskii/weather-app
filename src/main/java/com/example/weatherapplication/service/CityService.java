package com.example.weatherapplication.service;

import com.example.weatherapplication.model.City;

import java.util.List;
import java.util.Optional;

public interface CityService {
    List<City> findAll();
    Optional<City> Save(String city);
}
