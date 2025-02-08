package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.api.WeatherResponse;
import net.engineeringdigest.journalApp.cache.AppCache;
import net.engineeringdigest.journalApp.constants.Placeholders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String accessApiKey;

//    private static final String api = "http://api.weatherstack.com/current?access_key=accessKey&query=CITY";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RedisService redisService;

    public WeatherResponse getWeatherResponse(String city) {
        WeatherResponse response = redisService.get("weather_of_" + city, WeatherResponse.class);
        if (response != null) {
            return response;
        } else {
            String finalApi = appCache.appCache.get(AppCache.keys.WEATHER_API.toString())
                    .replace(Placeholders.accessKey, accessApiKey).replace(Placeholders.city, city);
            ResponseEntity<WeatherResponse> weatherResponse =
                    restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
            HttpStatus statusCode = weatherResponse.getStatusCode();
            WeatherResponse body = weatherResponse.getBody();
            if (body != null) {
                redisService.set("weather_of_" + city, body, 300l);
            }
            return body;
        }
    }
}
