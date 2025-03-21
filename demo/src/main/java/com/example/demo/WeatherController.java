package com.example.demo;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @GetMapping("/{city}")
    public ResponseEntity<String> getWeather(@PathVariable String city) {
        // Call the static method directly
        String weatherData = WeatherApp.getWeather(city);
        return ResponseEntity.ok(weatherData);
    }
}
