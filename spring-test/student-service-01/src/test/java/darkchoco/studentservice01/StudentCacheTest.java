package darkchoco.studentservice01;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class StudentCacheTest {

    @Autowired
    private StudentService studentService;

    @MockBean
    private StudentRepository studentRepository;

    @Test
    void getStudentByName_forMultipleRequests_isRetrivedFromCache() {
        //given
        String name = "Ikhee";
        given(studentRepository.getStudentByName(name)).willReturn(new Student("Ikhee"));

        //when
        studentService.getStudentByName(name);
        studentService.getStudentByName(name);
        studentService.getStudentByName(name);

        //then
        then(studentRepository).should(times(1)).getStudentByName(name);
    }
}
