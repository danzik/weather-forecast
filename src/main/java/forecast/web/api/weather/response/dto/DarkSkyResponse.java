package forecast.web.api.weather.response.dto;

import forecast.model.ConditionType;
import forecast.model.TemperatureUnit;
import forecast.web.model.ObjectList;

import java.util.Date;

public class DarkSkyResponse extends ObjectList<DarkSkyResponse> {
    private Double wind;
    private Double humidity;
    private Date currentDate;
    private Double temperature;
    private String currentCity;
    private ConditionType condition;
    private TemperatureUnit temperatureUnit;

    public Date getCurrentDate() {
        return currentDate;
    }

    public String getCurrentCity() {
        return currentCity;
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

    public TemperatureUnit getTemperatureUnit() {
        return temperatureUnit;
    }
}
