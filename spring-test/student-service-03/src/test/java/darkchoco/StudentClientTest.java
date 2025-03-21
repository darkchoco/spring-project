package darkchoco;

import darkchoco.studentservice03.Student;
import darkchoco.studentservice03.StudentClient;
import darkchoco.studentservice03.StudentService03Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(classes = StudentService03Application.class)
@AutoConfigureWireMock
public class StudentClientTest {

    @Autowired
    private StudentClient studentClient;

    @Test
    void getStudent_forGivenStudent_isReturned() {
        //given
        Long id = 1L;
        stubFor(get("/students/" + id).willReturn(okJson("""
                {
                "id": 1,
                "name": "Soojeong",
                "grade": 10
                }
                """)));

        //when
        Student student = studentClient.getStudent(id);

        //then
        then(student.getId()).isNotNull();
        then(student.getName()).isEqualTo("Soojeong");
        then(student.getGrade()).isEqualTo(10);
    }
}
