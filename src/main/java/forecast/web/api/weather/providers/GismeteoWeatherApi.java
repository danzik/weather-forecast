package forecast.web.api.weather.providers;

import forecast.location.Location;
import forecast.model.WeatherData;
import forecast.web.api.weather.model.WeatherProviderApi;
import forecast.web.api.weather.response.dto.GismeteoResponse;
import forecast.web.api.weather.response.WeatherProviderResponse;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class
GismeteoWeatherApi extends WeatherProviderApi {
    public GismeteoWeatherApi(String baseUri) {
        super(baseUri);
    }

    public List<Location> getWeatherData(Date date, List<String> locations) {
        Map<String, Object> queryParameters = new HashMap<>();

        queryParameters.put("locations", locations.toArray());

        WeatherProviderResponse<List<GismeteoResponse>> gismeteoResponse = getFullList("/gismeteo/date/{date}/", GismeteoResponse.class, queryParameters);

        return convertWeatherFromGistmeteoResponse(gismeteoResponse);
    }

    private List<Location> convertWeatherFromGistmeteoResponse(WeatherProviderResponse<List<GismeteoResponse>> responseFromProvider) {
        List<GismeteoResponse> body = responseFromProvider.getBody();

        List<Location> result = body.stream()
                .map(response -> {
                    WeatherData googleWeatherData = new WeatherData(response.getDate(),
                            response.getWindPercent(),
                            response.getHumidity(),
                            response.getTemperature(),
                            response.getCondition(),
                            response.getTemperatureUnit()

                    );
                    return new Location(response.getCity(), googleWeatherData);
                })
                .collect(Collectors.toList());

        return result;
    }
}
