package forecast.web.api.weather.providers;

import forecast.model.TemperatureUnit;
import forecast.location.Location;
import forecast.model.WeatherData;
import forecast.web.api.weather.model.WeatherProviderApi;
import forecast.web.api.weather.response.dto.AccuResponse;
import forecast.web.api.weather.response.WeatherProviderResponse;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AccuWeatherApi extends WeatherProviderApi {
    public AccuWeatherApi(String baseUri) {
        super(baseUri);
    }

    @Override
    public List<Location> getWeatherData(Date date, List<String> locations) {
        Map<String, Object> queryParameters = new HashMap<>();

        queryParameters.put("date", date);
        queryParameters.put("locations", locations);

        WeatherProviderResponse<List<AccuResponse>> response = getFullList("/accu/data/{date}/locations", AccuResponse.class, queryParameters);

        return convertWeatherFromAccuFormat(response);
    }

    private List<Location> convertWeatherFromAccuFormat(WeatherProviderResponse<List<AccuResponse>> responseFromProvider) {
        List<AccuResponse> body = responseFromProvider.getBody();

        List<Location> result = body.stream()
                .map(response -> {
                    WeatherData accuWeatherDate = new WeatherData(response.getDate(),
                            response.getWindValue(),
                            response.getHumidityValue(),
                            response.getTemperatureValue(),
                            response.getConditionValue(),
                            TemperatureUnit.CELSIUS

                    );
                    return new Location(response.getCurrentCity(), accuWeatherDate);
                })
                .collect(Collectors.toList());

        return result;
    }
}