package com.example.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URI;


public class WeatherApp {
    private static final String API_KEY = "30d9b7c88db185c10b932db8b881e630"; // Replace with your actual API key
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather?q=";

    // Method to fetch weather data from API
    public static String getWeather(String city) {
        try {
            // Construct API URL
            String urlString = BASE_URL + city + "&appid=" + API_KEY + "&units=metric";
            // New (Fixed)
URL url = new URI(urlString).toURL();

            
            // Open HTTP connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read response
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            return response.toString(); // Return JSON response as String
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    // Method to extract and display weather data from JSON response
    public static void parseAndDisplayWeather(String jsonResponse) {
        try {
            // Extract city name
            String cityName = jsonResponse.split("\"name\":\"")[1].split("\"")[0];
            // Extract temperature
            String temperature = jsonResponse.split("\"temp\":")[1].split(",")[0];
            // Extract weather description
            String weatherDescription = jsonResponse.split("\"description\":\"")[1].split("\"")[0];

            // Display results
            System.out.println("City: " + cityName);
            System.out.println("Temperature: " + temperature + "°C");
            System.out.println("Weather: " + weatherDescription);
        } catch (Exception e) {
            System.out.println("Error parsing weather data: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String city = "Dubai"; // Change the city name as needed
        String weatherData = getWeather(city);
        parseAndDisplayWeather(weatherData);
    }
}
