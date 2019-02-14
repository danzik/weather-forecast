package forecast.web.http;

import java.net.URI;

public final class RestTemplate<T> {

    public ResponseEntity<T> exchange(String baseUri, Class<T> responseType) {return null;}

    public ResponseEntity<T> exchange(URI uri, Class<T> responseType) {return null;}
}
