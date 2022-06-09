package com.example.weatherapplication.model;

import lombok.Data;

@Data
public class Current {
    private String last_updated;
    private String temp_c;
    private String temp_f;
    private String wind_kph;
    private String wind_dir;
    private String humidity;
    private String feelslike_c;
    private String feelslike_f;
    private String cloud;
    private Condition condition;
    private String uv;
    private String pressure_mb;
    private air_quality air_quality;
    private String precip_mm;

    public String getTemp_c() {
        return String.format("%sºC", temp_c);
    }

    public String getFeelslike_c() {
        return String.format("%sºC", feelslike_c);
    }
}
