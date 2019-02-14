package forecast.web.model;

import java.util.List;

public class ObjectList<T> implements ResponseListObject<T> {
    private Links links;
    private List<T> items;

    @Override
    public List<T> getItems() {
        return items;
    }

    @Override
    public String getNextRef() {
        return links != null ? links.getNext() : null;
    }
}
