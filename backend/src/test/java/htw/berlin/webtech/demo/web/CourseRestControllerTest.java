package htw.berlin.webtech.demo.web;

import htw.berlin.webtech.demo.service.CourseService;
import htw.berlin.webtech.demo.web.api.Course;
import htw.berlin.webtech.demo.web.api.Day;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CourseRestController.class)
class CourseRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    @Test
    @DisplayName("should return found courses from course service")
    void returns_found_course_from_service() throws Exception {
        //given
        var courses = List.of(
                new Course(1, "Englisch", Day.Montag, LocalTime.MIN, LocalTime.MAX),
                new Course(2, "Mathe", Day.Dienstag, LocalTime.NOON, LocalTime.MIDNIGHT)
        );
        doReturn(courses).when(courseService).findAll();
        //when
        mockMvc.perform(get("/api/v1/courses"))
            //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Englisch"))
                .andExpect(jsonPath("$[0].day").value(Day.Montag))
                .andExpect(jsonPath("$[0].start").value(LocalTime.MIN))
                .andExpect(jsonPath("$[0].end").value(LocalTime.MAX))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[0].name").value("Mathe"))
                .andExpect(jsonPath("$[0].day").value(Day.Dienstag))
                .andExpect(jsonPath("$[0].start").value(LocalTime.NOON))
                .andExpect(jsonPath("$[0].end").value(LocalTime.MIDNIGHT));
    }

    @Test
    @DisplayName("should return 404 if course is not found")
    void should_return_404_if_course_is_not_found() throws Exception {
        // given
        doReturn(null).when(courseService).findById(anyLong());

        // when
        mockMvc.perform(get("/api/v1/course/123"))
                // then
                .andExpect(status().isNotFound());
    }

    /*@Test
    @DisplayName("should return 201 http status and Location header when creating a course")
    void should_return_201_http_status_and_location_header_when_creating_a_course() throws Exception {
        // given
        String courseToCreateAsJson = "{\"name\": \"Deutsch\", \"day\":\"Montag\", \"start\":\"12:00:00\", \"ende\": 13:00:00}";
        var course = new Course(123, null, null, null, null);
        doReturn(course).when(courseService).create(any());

        // when
        mockMvc.perform(
                        post("/api/v1/course")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(courseToCreateAsJson)
                )
                // then
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", Matchers.equalTo(("/api/v1/course/" + course.getId()))));
//            .andExpect(header().string("Location", Matchers.containsString(Long.toString(person.getId()))));

    }*/
/*
    @Test
    @DisplayName("should validate create course request")
    void should_validate_craete_course_request() throws Exception {
        String courseToCreateAsJson = "{\"name\": \"Deutsch\", \"day\":\"Montag\", \"start\":\"12:00:00\", \"ende\":\"13:00:00\"}";

        // when
        mockMvc.perform(
                post("/api/v1/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(courseToCreateAsJson)
                        .andExpectstatus().isBadRequest());
    }*/
}