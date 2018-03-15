package Filtering;

import logic.Event;

public interface Criterion {

    boolean complies(Event event);

}
