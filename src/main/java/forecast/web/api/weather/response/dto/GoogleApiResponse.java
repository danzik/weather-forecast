package forecast.web.api.weather.response.dto;

import forecast.model.ConditionType;
import forecast.model.TemperatureUnit;
import forecast.web.model.ObjectList;

import java.util.Date;

public class GoogleApiResponse extends ObjectList<GoogleApiResponse> {
    private Date date;
    private String city;
    private Double wind;
    private Double humidity;
    private Double temperature;
    private ConditionType condition;
    private TemperatureUnit temperatureUnit;

    public GoogleApiResponse() {}

    public GoogleApiResponse(Date date, String city, Double wind, Double humidity, Double temperature,
                             ConditionType condition, TemperatureUnit temperatureUnit) {
        this.date = date;
        this.city = city;
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
    {}
    public Double getTemperature() {
        return temperature;
    }

    public ConditionType getCondition() {
        return condition;
    }

    public TemperatureUnit getTemperatureUnit() {
        return temperatureUnit;
    }

    public String getCity() {
        return city;
    }
}
