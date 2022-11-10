package htw.berlin.webtech.demo.service;

import htw.berlin.webtech.demo.persistence.CourseEntity;
import htw.berlin.webtech.demo.persistence.CourseRepository;
import htw.berlin.webtech.demo.web.api.Course;
import htw.berlin.webtech.demo.web.api.CourseCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> findAll(){
        List<CourseEntity> courses = courseRepository.findAll();
        return courses.stream()
                .map(this::transformEntity)
                .collect(Collectors.toList());
    }

    public Course findById(Long id){
        var courseEntity = courseRepository.findById(id);
        return courseEntity.map(this::transformEntity).orElse(null);
    }

    public Course create(CourseCreateRequest request){
        var courseEntity = new CourseEntity(request.getName(), request.getDay(), request.getStart(), request.getEnde());
        courseEntity = courseRepository.save(courseEntity);
        return transformEntity(courseEntity);
    }

    private Course transformEntity(CourseEntity courseEntity){
        return new Course(
                courseEntity.getId(),
                courseEntity.getName(),
                courseEntity.getDay(),
                courseEntity.getStart(),
                courseEntity.getEnde()
        );
    }
}
