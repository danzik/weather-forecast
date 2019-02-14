package forecast.controller.weather;

import forecast.location.Location;
import forecast.web.api.weather.model.WeatherProviderApi;

import java.util.Date;
import java.util.List;

public class WeatherController {
    private WeatherProviderApi weatherApi;

    public WeatherController(WeatherProviderApi providerApi) {
        this.weatherApi = providerApi;
    }

    public List<Location> discoveryWeatherDataByDateAndLocation(Date date, List<String> locations) {
        return weatherApi.getWeatherData(date, locations);
    }

}