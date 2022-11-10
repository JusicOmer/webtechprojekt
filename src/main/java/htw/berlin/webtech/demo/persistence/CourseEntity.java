package htw.berlin.webtech.demo.persistence;

import htw.berlin.webtech.demo.web.api.Day;

import javax.persistence.*;
import java.time.LocalTime;

@Entity(name  = "courses")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "day", nullable = false)
    private Day day;

    @Column(name = "start", nullable = false)
    private LocalTime start;

    @Column(name = "ende", nullable = false)
    private LocalTime ende;

    public CourseEntity(long id, String name, Day day, LocalTime start, LocalTime ende) {
        this.id = id;
        this.name = name;
        this.day = day;
        this.start = start;
        this.ende = ende;
    }

    protected CourseEntity(){

    }

    public long getId() {
        return id;
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
