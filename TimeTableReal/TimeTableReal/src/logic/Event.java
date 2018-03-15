package logic;

import java.util.Objects;

public abstract class Event {

    private String name;
    private int startTime;
    private int endTime;
    private WeekDays day;

    public Event(String name, int startTime, int endTime, String day) throws Exception {
        if (startTime < 0 || startTime > 24 || endTime < 0 || endTime > 25) {
            throw new IllegalArgumentException("Not a valid time");
        }
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;

        if (Objects.equals(day, "MON")) {
            this.day = WeekDays.MON;
        } else if (Objects.equals(day, "TUE")) {
            this.day = WeekDays.TUE;
        } else if (Objects.equals(day, "WED")) {
            this.day = WeekDays.WED;
        } else if (Objects.equals(day, "THU")) {
            this.day = WeekDays.THU;
        } else if (Objects.equals(day, "FRI")) {
            this.day = WeekDays.FRI;
        } else if (Objects.equals(day, "SAT")) {
            this.day = WeekDays.SAT;
        } else if (Objects.equals(day, "SUN")) {
            this.day = WeekDays.SUN;
        } else {
            throw new IllegalArgumentException("Not a valid day");
        }
    }

    @Override
    public String toString() {
        return this.name + "\n" + this.startTime + " - " + this.endTime + " on " + this.day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public WeekDays getDay() {
        return day;
    }

    public void setDay(WeekDays day) {
        this.day = day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        Event event = (Event) o;
        return getStartTime() == event.getStartTime() &&
                getEndTime() == event.getEndTime() &&
                Objects.equals(getName(), event.getName()) &&
                getDay() == event.getDay();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getName(), getStartTime(), getEndTime(), getDay());
    }
}
