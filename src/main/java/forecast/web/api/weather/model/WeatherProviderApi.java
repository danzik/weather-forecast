package forecast.web.api.weather.model;

import forecast.location.Location;
import forecast.web.api.weather.response.WeatherProviderResponse;
import forecast.web.exception.RestException;
import forecast.web.http.ResponseEntity;
import forecast.web.model.ResponseListObject;
import forecast.web.http.RestTemplate;
import forecast.web.http.UriTemplateHandler;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

public abstract class WeatherProviderApi {
    protected final String baseUri;
    protected final RestTemplate restTemplate;
    protected final UriTemplateHandler urlHandler;

    public WeatherProviderApi(String baseUri) {
        this.baseUri = baseUri;
        this.restTemplate = new RestTemplate();
        this.urlHandler = new UriTemplateHandler();
    }

    public <E, T extends ResponseListObject<E>> WeatherProviderResponse<List<E>> getFullList(String url, Class<T> responseType, Map<String, ?> uriVariables) {
        List<E> list = new ArrayList<>();

        WeatherProviderResponse<Void> weatherProviderResponse = iterateFullList(url, responseType, uriVariables, list::addAll);

        if (weatherProviderResponse.isOk()) {
            return WeatherProviderResponse.ok(list, weatherProviderResponse.getStatusCode());
        }

        return WeatherProviderResponse.error(weatherProviderResponse.getError(), weatherProviderResponse.getStatusCode());
    }

    public <E, T extends ResponseListObject<E>> WeatherProviderResponse<Void> iterateFullList(String url, Class<T> responseType, Map<String, ?> uriVariables, Consumer<List<E>> consumer) {
        try {
            ResponseEntity<T> entity = restTemplate.exchange(url, responseType);
            T body = entity.getBody();
            consumer.accept(body.getItems());
            String nextLink = body.getNextRef();

            while (nextLink != null) {
                Optional<ResponseEntity<T>> entityFromNextLink = getEntityFromNextLink(nextLink, responseType, uriVariables);
                if (entityFromNextLink.isPresent()) {
                    ResponseEntity<T> currentEntity = entityFromNextLink.get();
                    consumer.accept(currentEntity.getBody().getItems());
                    nextLink = currentEntity.getBody().getNextRef();
                } else {
                    nextLink = null;
                }
            }
            return WeatherProviderResponse.ok(null, entity.getStatusCodeValue());
        } catch (RestException ex) {
            return WeatherProviderResponse.error(ex.getStatusText(), ex.getStatusCode());
        }
    }

    private <T extends ResponseListObject<?>> Optional<ResponseEntity<T>> getEntityFromNextLink(String nextLink, Class<T> responseType, Map<String, ?> uriParameters) {
        URI nextUri = urlHandler.expand(nextLink, uriParameters);
        try {
            ResponseEntity<T> nextEntity = restTemplate.exchange(nextUri, responseType);
            return Optional.of(nextEntity);
        } catch (RestException e) {
            return Optional.empty();
        }
    }

    public abstract List<Location> getWeatherData(Date date, List<String> locations);

}