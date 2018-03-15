package logic;

import com.sun.tools.corba.se.idl.StringGen;

public class Lecture extends Event {

    private String teacher;
    private String location;

    public Lecture(String name, int startTime, int endTime, String day, String teacher, String location) throws Exception{
        super(name, startTime, endTime, day);
        this.teacher = teacher;
        this.location = location;
    }

    public Lecture(String name, int startTime, int endTime, String day) throws Exception {
        super(name,startTime,endTime,day);
    }

    @Override
    public String toString() {
        String s = super.toString();
        s += "\nTeacher: " + this.teacher + ", location: " + this.location;
        return s;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
