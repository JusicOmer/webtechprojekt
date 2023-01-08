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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(CourseRestController.class)
class CourseRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

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


    @Test
    @DisplayName("should validate create course request")
    void should_validate_craete_course_request() throws Exception {
        String courseToCreateAsJson = "{\"name\": \"\", \"day\":\"Montag\", \"start\":\"12:00:00\", \"ende\":\"13:00:00\"}";

        // when
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(courseToCreateAsJson))
                        .andExpect(status().isBadRequest());
    }
}
