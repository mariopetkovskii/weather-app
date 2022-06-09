package com.example.weatherapplication.service.impl;

import com.example.weatherapplication.model.City;
import com.example.weatherapplication.repository.jpa.CityRepository;
import com.example.weatherapplication.service.CityService;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository)
    {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public Optional<City> Save(String city) {
        City cityy = new City(city);
        this.cityRepository.delete(cityy);
        return Optional.of(this.cityRepository.save(new City(city)));
    }

}
