package com.example.weatherapplication.service;

import com.example.weatherapplication.model.Foo;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

public interface WeatherService {
    void getInfo(String city, RestTemplate restTemplate, Model model);
}
