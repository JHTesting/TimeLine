package Filtering;

import logic.Event;

public class TimeFilter implements Criterion {

    private int time;

    public TimeFilter(int time) {
        this.time = time;
    }

    public boolean complies(Event event) {

        if (event.getStartTime() == time || event.getEndTime() == time) {
            return true;
        }

        return false;
    }

    public boolean startComplies(Event event) {

        if (event.getStartTime() == time) {
            return true;
        }
        return false;
    }

    public boolean endComplies(Event event) {

        if (event.getEndTime() == time) {
            return true;
        }
        return false;
    }
}
