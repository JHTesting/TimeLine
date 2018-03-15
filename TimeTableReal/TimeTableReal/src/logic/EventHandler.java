package logic;

import Comparator.EventComparator;
import Filtering.Criterion;

import java.lang.reflect.Array;
import java.util.*;

public class EventHandler {

    private List<Event> eventList;
    private Map<WeekDays, List<Event>> eventMap;
    private List<String> eventTypes;

    public EventHandler(Collection<Event> events) {

        this.eventList = new ArrayList<>();

        ArrayList<Event> givenEvents = (ArrayList<Event>) events;

        for (int i = 0; i < givenEvents.size(); i++) {
            eventList.add(givenEvents.get(i));
        }

        this.eventMap = new HashMap<>();

        fillEventMap();

        this.eventTypes = new ArrayList<>();
        eventTypes.add("Lecture");
    }

    public EventHandler(Event event) {

        this.eventList = new ArrayList<>();

        eventList.add(event);

        this.eventMap = new HashMap<>();

        fillEventMap();
    }

    public EventHandler() {

        this.eventList = new ArrayList<>();

        this.eventMap = new HashMap<>();

        fillEventMap();

    }

    public boolean add(Event event) {

        if (eventMap.get(event.getDay()) != null ) {
            WeekDays day = event.getDay();

            eventList.add(event);

            eventMap.get(day).add(event);

            return true;
        } else {

            return false;
        }
    }


    public boolean remove(Event event) {
        if (contains(event)) {
            eventList.remove(event);
            eventMap.get(event.getDay()).remove(event);
            return true;
        }
        return false;
    }

    public boolean contains(Event event) {
        if (eventList.contains(event) && eventMap.get(event.getDay()).contains(event)) {
            return true;
        } return false;
    }

    public List<Event> showEventsOnDay(WeekDays day) {
        ArrayList<Event> a = (ArrayList) eventMap.get(day);
        Collections.sort(a, new EventComparator(a));
        return a;
    }

    public List<Event> showAllEvents() {
        Collections.sort(eventList, new EventComparator(eventList));
        return eventList;
    }

    public List<Event> eventsWhichComplyWith(Criterion c) {

        List<Event> complyingEvents = new ArrayList<>();

        for (Event event : eventList) {

            if (c.complies(event)) {
                complyingEvents.add(event);
            }
        }

        return complyingEvents;
    }

    public List<String> getEventTypes() {
        return eventTypes;
    }

    private void fillEventMap() {

        eventMap.put(WeekDays.MON, new ArrayList<>());
        eventMap.put(WeekDays.TUE, new ArrayList<>());
        eventMap.put(WeekDays.WED, new ArrayList<>());
        eventMap.put(WeekDays.THU, new ArrayList<>());
        eventMap.put(WeekDays.FRI, new ArrayList<>());
        eventMap.put(WeekDays.SAT, new ArrayList<>());
        eventMap.put(WeekDays.SUN, new ArrayList<>());
    }


}
