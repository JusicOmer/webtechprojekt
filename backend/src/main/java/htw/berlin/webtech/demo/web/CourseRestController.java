package htw.berlin.webtech.demo.web;

import htw.berlin.webtech.demo.service.CourseService;
import htw.berlin.webtech.demo.web.api.Course;
import htw.berlin.webtech.demo.web.api.CourseManipulationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
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

    @GetMapping(path = "/api/v1/courses/{id}")
    public ResponseEntity<Course> fetchCourseById(@PathVariable Long id){
        var course = courseService.findById(id);
        return course != null? ResponseEntity.ok(course) : ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/api/v1/courses")
    public ResponseEntity<List<Course>> createCourses(@RequestBody CourseManipulationRequest request) throws URISyntaxException {
        var valid = validate(request);
        if (valid) {
            var course = courseService.create(request);
            URI uri = new URI("/api/v1/courses" + course.getId());
            return ResponseEntity.created(uri).build();
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(path = "/api/v1/courses/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody CourseManipulationRequest request){
        var course = courseService.update(id, request);
        return course != null ? ResponseEntity.ok(course) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/api/v1/courses/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id){
        boolean successful = courseService.deleteById(id);
        return successful ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    private boolean validate(CourseManipulationRequest request){
        return request.getName() != null
                && !request.getName().isBlank()
                && request.getDay() != null
                && !request.getDay().toString().isBlank() &&
                request.getStart() != null
                && !request.getStart().toString().isBlank() &&
                request.getEnde() != null
                && !request.getEnde().toString().isBlank();
    }
}
