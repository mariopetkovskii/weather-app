package com.example.weatherapplication.model;

import lombok.Data;

@Data
public class Location {
    private String name;
    private String region;
    private String country;
    private String localtime;
    private String last_updated;
    private String message;
    private Error error;

}
