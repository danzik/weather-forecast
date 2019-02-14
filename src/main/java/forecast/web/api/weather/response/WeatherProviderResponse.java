package forecast.web.api.weather.response;

public class WeatherProviderResponse<T> {
    private T body;
    private String error;
    private int statusCode;

    public static <T> WeatherProviderResponse<T> ok(T body, Integer statusCode) {
        return new WeatherProviderResponse<>(body, null, statusCode);
    }

    public static <T> WeatherProviderResponse<T> error(String error, Integer statusCode) {
        return new WeatherProviderResponse<T>(null, error, statusCode);
    }

    public WeatherProviderResponse(T body, String error, Integer statusCode) {
        this.body = body;
        this.error = error;
        this.statusCode = statusCode;
    }

    public boolean isOk() {
        return statusCode == 200;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
