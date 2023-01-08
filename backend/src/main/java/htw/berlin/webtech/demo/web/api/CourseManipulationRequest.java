package htw.berlin.webtech.demo.web.api;

import javax.validation.constraints.NotBlank;
import java.time.LocalTime;

public class CourseManipulationRequest {
    @NotBlank(message = "Please fill in the blank.")
    private String name;
    @NotBlank(message = "Please fill in the blank.")
    private Day day;
    @NotBlank
    private LocalTime start;
    @NotBlank
    private LocalTime ende;

    public CourseManipulationRequest(String name, Day day, LocalTime start, LocalTime ende) {
        this.name = name;
        this.day = day;
        this.start = start;
        this.ende = ende;
    }

    public CourseManipulationRequest(){}

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
