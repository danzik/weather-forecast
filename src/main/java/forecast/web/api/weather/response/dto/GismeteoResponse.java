package forecast.web.api.weather.response.dto;

import forecast.model.ConditionType;
import forecast.model.TemperatureUnit;
import forecast.web.model.ObjectList;

import java.util.Date;

public class GismeteoResponse extends ObjectList<GismeteoResponse> {
    private Date date;
    private String city;
    private Double humidity;
    private Double windPercent;
    private Double temperature;
    private ConditionType condition;
    private TemperatureUnit temperatureUnit;

    public Date getDate() {
        return date;
    }

    public String getCity() {
        return city;
    }

    public Double getWindPercent() {
        return windPercent;
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
