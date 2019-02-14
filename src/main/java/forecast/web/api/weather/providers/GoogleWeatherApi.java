package forecast.web.api.weather.providers;

        import forecast.location.Location;
        import forecast.model.WeatherData;
        import forecast.web.api.weather.model.WeatherProviderApi;
        import forecast.web.api.weather.response.dto.GoogleApiResponse;
        import forecast.web.api.weather.response.WeatherProviderResponse;

        import java.util.Date;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;
        import java.util.stream.Collectors;

public class GoogleWeatherApi extends WeatherProviderApi {

    public GoogleWeatherApi(String baseUri) {
        super(baseUri);
    }

    @Override
    public List<Location>  getWeatherData(Date date, List<String> locations) {
        Map<String, Object> queryParameters = new HashMap<>();

        queryParameters.put("locations", locations.toArray());

        WeatherProviderResponse<List<GoogleApiResponse>> fullList = getFullList("/google/date/{date}", GoogleApiResponse.class, queryParameters);

        return convertWeatherFromGoogleFormat(fullList);
    }

    private List<Location> convertWeatherFromGoogleFormat(WeatherProviderResponse<List<GoogleApiResponse>> responseFromProvider) {
        List<GoogleApiResponse> body = responseFromProvider.getBody();

        List<Location> result = body.stream()
                .map(response -> {
                    WeatherData googleWeatherData = new WeatherData(response.getDate(),
                            response.getWind(),
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