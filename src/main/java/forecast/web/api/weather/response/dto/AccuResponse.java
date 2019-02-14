package forecast.web.api.weather.response.dto;

import forecast.model.ConditionType;
import forecast.model.TemperatureUnit;
import forecast.web.model.ObjectList;

import java.util.Date;

public class AccuResponse extends ObjectList<AccuResponse> {
    private Date date;
    private Double windValue;
    private String currentCity;
    private Double humidityValue;
    private Double temperatureValue;
    private ConditionType conditionValue;
    private TemperatureUnit temperatureUnitValue;

    public Date getDate() {
        return date;
    }

    public Double getWindValue() {
        return windValue;
    }

    public Double getHumidityValue() {
        return humidityValue;
    }

    public Double getTemperatureValue() {
        return temperatureValue;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public ConditionType getConditionValue() {
        return conditionValue;
    }

    public TemperatureUnit getTemperatureUnitValue() {
        return temperatureUnitValue;
    }
}