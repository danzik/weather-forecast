package forecast.web.api.weather.providers;

import forecast.location.Location;
import forecast.model.WeatherData;
import forecast.web.api.weather.model.WeatherProviderApi;
import forecast.web.api.weather.response.dto.DarkSkyResponse;
import forecast.web.api.weather.response.WeatherProviderResponse;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DarkSkyWeatherApi extends WeatherProviderApi {
    public DarkSkyWeatherApi(String baseUri) {
        super(baseUri);
    }

    public List<Location> getWeatherData(Date date, List<String> locations) {
        Map<String, Object> queryParameters = new HashMap<>();

        queryParameters.put("locations", locations.toArray());

        WeatherProviderResponse<List<DarkSkyResponse>> response = getFullList("/dark-sky/date/{date}/", DarkSkyResponse.class, queryParameters);

        return convertWeatherFromDarkSkyFormat(response);
    }

    private List<Location> convertWeatherFromDarkSkyFormat(WeatherProviderResponse<List<DarkSkyResponse>> responseFromProvider) {
        List<DarkSkyResponse> body = responseFromProvider.getBody();

        List<forecast.location.Location> result = body.stream()
                .map(response -> {
                    WeatherData googleWeatherData = new WeatherData(response.getCurrentDate(),
                            response.getWind(),
                            response.getHumidity(),
                            response.getTemperature(),
                            response.getCondition(),
                            response.getTemperatureUnit()
                    );
                    return new forecast.location.Location(response.getCurrentCity(), googleWeatherData);
                })
                .collect(Collectors.toList());

        return result;
    }
}
