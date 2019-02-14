package forecast.location;

import forecast.model.WeatherData;

import java.util.Objects;

public class Location {
    private Long id;
    private String cityName;
    private WeatherData weatherData;

    public Location(String cityName, WeatherData weatherData) {
        this.cityName = cityName;
        this.weatherData = weatherData;
    }

    public Long getId() {
        return id;
    }

    public String getCityName() {
        return cityName;
    }

    public WeatherData getWeatherData() {
        return weatherData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(id, location.id) &&
                Objects.equals(cityName, location.cityName) &&
                Objects.equals(weatherData, location.weatherData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cityName, weatherData);
    }
}