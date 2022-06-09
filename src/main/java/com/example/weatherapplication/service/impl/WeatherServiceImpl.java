package com.example.weatherapplication.service.impl;

import com.example.weatherapplication.model.Foo;
import com.example.weatherapplication.model.exceptions.CityNotFoundException;
import com.example.weatherapplication.service.ErrorHandler.RestTemplateResponseErrorHandler;
import com.example.weatherapplication.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;


@ContextConfiguration(classes = { CityNotFoundException.class, Foo.class })
@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private RestTemplateBuilder builder;

    @Value("${api.key}")
    private String apiKey;

    @Autowired
    public WeatherServiceImpl(RestTemplateBuilder restTemplateBuilder) {
            restTemplateBuilder
                    .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }


    @Override()
    public void getInfo(String city, RestTemplate restTemplate, Model model) {
        String resourceUrl = "http://api.weatherapi.com/v1/current.json?key="+ this.apiKey +"&q=" + city + "&aqi=yes";

        restTemplate = this.builder
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();

        Foo response = restTemplate
                .getForObject(resourceUrl, Foo.class);

            model.addAttribute("items", response);
    }


}
