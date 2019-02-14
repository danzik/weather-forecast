package forecast.web.exception;

// Dummy exception
public class RestException extends RuntimeException {

    private final int statusCode;
    private final String statusText;

    public RestException(String message, Integer statusCode, String statusText) {
        super(message);
        this.statusCode = 200;
        this.statusText = "Default status text";
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusText() {
        return statusText;
    }
}
