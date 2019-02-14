package forecast;

import forecast.controller.weather.WeatherController;
import forecast.location.Location;
import forecast.model.ConditionType;
import forecast.model.TemperatureUnit;
import forecast.model.WeatherData;
import forecast.web.api.weather.providers.GoogleWeatherApi;
import forecast.web.api.weather.response.WeatherProviderResponse;
import forecast.web.api.weather.response.dto.GoogleApiResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ForecastApiClientTest {

    @Mock
    private GoogleWeatherApi googleWeatherApiMock;

    @InjectMocks
    private WeatherController weatherControllerMock;

    @Test
    void getWeatherDataWithFewLocations() {
        Date currentDate = new Date();

        WeatherData weatherDataForKiev = new WeatherData(currentDate, 0.75, 0.75, 37.0, ConditionType.SUNNY, TemperatureUnit.CELSIUS);
        WeatherData weatherDataForKharkiv = new WeatherData(currentDate, 0.91, 0.74, 38.0, ConditionType.CLOUDY, TemperatureUnit.CELSIUS);

        Location kiev = new Location("Kiev", weatherDataForKiev);
        Location kharkiv = new Location("Kharkiv", weatherDataForKharkiv);

        List<Location> expectedResult = new ArrayList<>();
        expectedResult.add(kiev);
        expectedResult.add(kharkiv);

        List<String> nameLocations = new ArrayList<>();

        nameLocations.add("Kiev");
        nameLocations.add("Kharkiv");

        GoogleApiResponse googleResponseForKiev = new GoogleApiResponse(currentDate,
                "Kiev", 0.75, 0.75, 37.0, ConditionType.SUNNY, TemperatureUnit.CELSIUS);
        GoogleApiResponse googleResponseForKharkiv = new GoogleApiResponse(currentDate, "Kharkiv",0.91,
                0.74, 38.0, ConditionType.CLOUDY, TemperatureUnit.CELSIUS);

        List<GoogleApiResponse> googleApiResponses = new ArrayList<>();
        googleApiResponses.add(googleResponseForKiev);
        googleApiResponses.add(googleResponseForKharkiv);

        WeatherProviderResponse<List<GoogleApiResponse>> googleResponse = WeatherProviderResponse.ok(googleApiResponses, 200);
        when(googleWeatherApiMock.getWeatherData(any(), anyList())).thenCallRealMethod();
        when(googleWeatherApiMock.getFullList(any(), eq(GoogleApiResponse.class), any())).thenReturn(googleResponse);

        List<Location> result = weatherControllerMock.discoveryWeatherDataByDateAndLocation(currentDate, nameLocations);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void getWeatherDataWithCustomTemperatureUnit() {
        Date currentDate = new Date();
        Double fromCelsiusToFahrenheit = TemperatureUnit.CELSIUS.toFahreneit(37.0);
        WeatherData weatherDataForKiev = new WeatherData(currentDate, 0.75, 0.75, 37.0, ConditionType.SUNNY, TemperatureUnit.CELSIUS);

        GoogleApiResponse googleResponseForKiev = new GoogleApiResponse(currentDate,
                "Kiev", 0.75, 0.75, 37.0, ConditionType.SUNNY, TemperatureUnit.CELSIUS);

        WeatherProviderResponse<List<GoogleApiResponse>> googleResponse = WeatherProviderResponse.ok(Collections.singletonList(googleResponseForKiev), 200);

        when(googleWeatherApiMock.getWeatherData(any(), anyList())).thenCallRealMethod();
        when(googleWeatherApiMock.getFullList(any(), eq(GoogleApiResponse.class), any())).thenReturn(googleResponse);

        List<Location> result = weatherControllerMock.discoveryWeatherDataByDateAndLocation(currentDate, Collections.singletonList("Kiev"));
        Optional<Location> kievLocation = result.stream().findFirst();

        assertTrue(kievLocation.isPresent());
        assertEquals(kievLocation.get().getWeatherData(), weatherDataForKiev);
        assertEquals(fromCelsiusToFahrenheit, kievLocation.get().getWeatherData().getTemperature(TemperatureUnit.FAHRENHEIT));
    }
}
