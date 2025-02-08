package net.engineeringdigest.journalApp.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse {
    public Current current;

    @Getter
    @Setter
    public static class Current {
        @JsonProperty("observation_time")
        public String observation_time;
        public int feelslike;
    }

}
