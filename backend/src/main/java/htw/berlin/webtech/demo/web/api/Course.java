package htw.berlin.webtech.demo.web.api;

import java.time.LocalTime;

public class Course {
    private long id;
    private String name;
    private Day day;
    private LocalTime start;
    private LocalTime ende;

    public Course(long id, String name, Day day, LocalTime start, LocalTime ende) {
        this.id = id;
        this.name = name;
        this.day = day;
        this.start = start;
        this.ende = ende;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnde() {
        return ende;
    }

    public void setEnde(LocalTime ende) {
        this.ende = ende;
    }
}
