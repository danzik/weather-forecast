package forecast.web.model;

import java.util.List;

public interface ResponseListObject<T> {

    List<T> getItems();

    String getNextRef();
}
