package darkchoco.studentservice01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class StudentServiceTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @DisplayName("Returning saved student from service layer")
    @Test
    void getStudentById_forSavedStudent_isReturned() {
        //given
        Student savedStudent = studentRepository.save(new Student("Soojeong"));

        //when
        Student student = studentService.getStudentById(savedStudent.getId());

        //then
        then(student.getName()).isEqualTo("Soojeong");
        then(student.getId()).isNotNull();
    }
}
