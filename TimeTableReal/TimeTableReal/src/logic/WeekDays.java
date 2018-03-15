package logic;

import java.lang.reflect.Array;

public enum WeekDays {
    MON("Monday"),
    TUE("Tuesday"),
    WED("Wednesday"),
    THU("Thursday"),
    FRI("Friday"),
    SAT("Saturday"),
    SUN("Sunday");

    private String day;

    private WeekDays(String day) {
        this.day = day;
    }

    public String getDay() {
        return day;
    }

}
