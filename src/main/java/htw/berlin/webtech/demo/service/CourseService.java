package htw.berlin.webtech.demo.service;

import htw.berlin.webtech.demo.persistence.CourseEntity;
import htw.berlin.webtech.demo.persistence.CourseRepository;
import htw.berlin.webtech.demo.web.api.Course;
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
                .map(courseEntity -> new Course(
                        courseEntity.getId(),
                        courseEntity.getName(),
                        courseEntity.getDay(),
                        courseEntity.getStart(),
                        courseEntity.getEnde()
                ))
                .collect(Collectors.toList());
    }
}
