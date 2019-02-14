package forecast.web.exception;

public class WeatherProviderError extends IllegalArgumentException {
    private String body;
    private Integer statusCode;

    public WeatherProviderError(String s) {
        super(s);
    }

    public String getBody() {
        return body;
    }

    public Integer getStatusCode() {
        return statusCode;
    }
}
