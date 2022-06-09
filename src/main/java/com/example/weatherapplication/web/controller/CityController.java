package com.example.weatherapplication.web.controller;

import com.example.weatherapplication.model.City;
import com.example.weatherapplication.model.Foo;
import com.example.weatherapplication.model.exceptions.CityNotFoundException;
import com.example.weatherapplication.service.CityService;
import com.example.weatherapplication.service.ErrorHandler.RestTemplateResponseErrorHandler;
import com.example.weatherapplication.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.web.servlet.function.RequestPredicates.method;

@Controller
@RequestMapping(value = {"/", "/home", "/gradovi"})
public class CityController {
    private final CityService cityService;
    private final WeatherService weatherService;

    @Value("${api.key}")
    private String apiKey;

    public CityController(CityService cityService, WeatherService weatherService) {
        this.cityService = cityService;
        this.weatherService = weatherService;
    }

    @GetMapping
    String getHomePage(@RequestParam(required = false) String error, Model model, HttpServletRequest req, RestTemplate restTemplate) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<City> cities = cityService.findAll();
        model.addAttribute("cities", cities);
        String search = (String) req.getSession().getAttribute("search");
        String resourceUrl;
        if(search != null){
            resourceUrl = "http://api.weatherapi.com/v1/current.json?key="+ this.apiKey + "&q=" + search + "&aqi=yes";
        }else {
            resourceUrl = "http://api.weatherapi.com/v1/current.json?key=" + this.apiKey + "&q=Skopje&aqi=yes";
        }
        Foo foo = restTemplate
                .getForObject(resourceUrl, Foo.class);
        model.addAttribute("items", foo);
        model.addAttribute("bodyContent", "gradovi");
        return "master-template";
    }

    @PostMapping("/search")
    public String searchFilter(HttpServletRequest req, Model model){
        String search = req.getParameter("search");
        req.getSession().setAttribute("search", search);
        List<City> cities = cityService.findAll();
        model.addAttribute("cities", cities);
        return "redirect:/gradovi";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addCitypage(Model model) {
        model.addAttribute("bodyContent", "add-form");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveCity(@RequestParam(required = false) Long id,
                           @RequestParam String grad) {
        this.cityService.Save(grad);
        return "redirect:/gradovi";
    }

    @PostMapping
    public String searchCityPage(Model model,
                                 RestTemplate restTemplate,
                                 @RequestParam String city) {
        try {
            weatherService.getInfo(city, restTemplate, model);
            List<City> cities = cityService.findAll();
            model.addAttribute("cities", cities);
            model.addAttribute("bodyContent", "gradovi");
            return "master-template";
        } catch (CityNotFoundException cityNotFoundException) {
            return "redirect:/gradovi?error=" + cityNotFoundException.getMessage();
        }
    }


}
