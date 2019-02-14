package forecast.model;

import java.util.Date;
import java.util.Objects;

public class WeatherData {
    protected Date date;
    protected Double wind;
    protected Double humidity;
    protected Double temperature;
    protected ConditionType condition;
    protected TemperatureUnit temperatureUnit;

    public WeatherData(TemperatureUnit temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
    }

    public WeatherData(Date date, Double wind, Double humidity, Double temperature, ConditionType condition,
                       TemperatureUnit temperatureUnit) {
        this.date = date;
        this.wind = wind;
        this.humidity = humidity;
        this.temperature = temperature;
        this.condition = condition;
        this.temperatureUnit = temperatureUnit;
    }

    public Date getDate() {
        return date;
    }

    public Double getWind() {
        return wind;
    }

    public Double getHumidity() {
        return humidity;
    }

    public Double getTemperature() {
        return temperature;
    }

    public ConditionType getCondition() {
        return condition;
    }

    public Double getTemperature(TemperatureUnit temperatureUnit) {
        if (this.temperatureUnit == temperatureUnit) { return temperature; }

        Double temperature = this.temperature;

        if (this.temperatureUnit != TemperatureUnit.CELSIUS) {
            temperature = this.temperatureUnit.toCelsius(temperature);
        }

        return temperatureUnit.convert(temperature, this.temperatureUnit);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setWind(Double wind) {
        this.wind = wind;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public void setCondition(ConditionType condition) {
        this.condition = condition;
    }

    public void setTemperatureUnit(TemperatureUnit temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherData that = (WeatherData) o;
        return Objects.equals(date, that.date) &&
                Objects.equals(wind, that.wind) &&
                Objects.equals(humidity, that.humidity) &&
                Objects.equals(temperature, that.temperature) &&
                condition == that.condition &&
                temperatureUnit == that.temperatureUnit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, wind, humidity, temperature, condition, temperatureUnit);
    }
}