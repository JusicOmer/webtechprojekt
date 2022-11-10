package htw.berlin.webtech.demo.web;

import htw.berlin.webtech.demo.service.CourseService;
import htw.berlin.webtech.demo.web.api.Course;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseRestController {

    /*private List<Course> courses;

    public CourseRestController(){
        courses = new ArrayList<>();
        courses.add(new Course(1, "Mathe", Day.Montag, LocalTime.of(8,00), LocalTime.of(9,30)));
        courses.add(new Course(2, "Englisch", Day.Dienstag, LocalTime.of(10,00), LocalTime.of(11,30)));
    }*/

    private final CourseService courseService;

    public CourseRestController(CourseService courseRepositoryService) {
        this.courseService = courseRepositoryService;
    }


    @GetMapping(path = "/api/v1/courses")
    public ResponseEntity<List<Course>> fetchCourses(){
        return ResponseEntity.ok(courseService.findAll());
    }
}
