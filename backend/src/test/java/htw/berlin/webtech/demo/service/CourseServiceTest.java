package htw.berlin.webtech.demo.service;

import htw.berlin.webtech.demo.persistence.CourseRepository;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest implements WithAssertions {

    @Mock
    private CourseRepository repository;

    @InjectMocks
    private CourseService underTest;

    @Test
    @DisplayName("should return true if delete was successful")
    void return_true_successful_delete(){
        //given
        Long givenId = 111L;
        doReturn(true).when(repository).existsById(givenId);

        //when
        boolean result = underTest.deleteById(givenId);

        //then
        verify(repository).deleteById(givenId);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("should return false if delete was successful")
    void return_false_successful_delete(){
        //given
        Long givenId = 111L;
        doReturn(false).when(repository).existsById(givenId);

        //when
        boolean result = underTest.deleteById(givenId);

        //then
        verifyNoMoreInteractions(repository);
        assertThat(result).isFalse();
    }

}
