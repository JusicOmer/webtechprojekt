package htw.berlin.webtech.demo.service;

import htw.berlin.webtech.demo.persistence.CourseEntity;
import htw.berlin.webtech.demo.persistence.CourseRepository;
import htw.berlin.webtech.demo.web.api.Course;
import htw.berlin.webtech.demo.web.api.CourseManipulationRequest;
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

    public Course create(CourseManipulationRequest request){
        var courseEntity = new CourseEntity(request.getName(), request.getDay(), request.getStart(), request.getEnde());
        courseEntity = courseRepository.save(courseEntity);
        return transformEntity(courseEntity);
    }

    public Course update(Long id, CourseManipulationRequest request){
        var courseEntityOptional = courseRepository.findById(id);
        if (courseEntityOptional.isEmpty()) return null;

        var courseEntity = courseEntityOptional.get();
        courseEntity.setDay(request.getDay());
        courseEntity.setEnde(request.getEnde());
        courseEntity.setName(request.getName());
        courseEntity.setStart(request.getStart());
        courseEntity = courseRepository.save(courseEntity);

        return transformEntity(courseEntity);
    }

    public boolean deleteById(Long id){
        if(!courseRepository.existsById(id)) {
            return false;
        }

        courseRepository.deleteById(id);
        return true;
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
