package Comparator;

import logic.Event;

import java.util.Comparator;
import java.util.List;

public class EventComparator implements Comparator<Event> {

    private List<Event> eventList;

    public EventComparator(List<Event> eventList) {
        this.eventList = eventList;
    }

    public int compare(Event e1, Event e2) {
        if (e1.getStartTime() == e2.getStartTime()) {
            return 0;
        } else if (e1.getStartTime() > e2.getStartTime()) {
            return 1;
        } else {
            return -1;
        }
    }
}
