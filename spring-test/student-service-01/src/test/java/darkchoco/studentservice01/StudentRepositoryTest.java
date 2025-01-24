package darkchoco.studentservice01;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.BDDAssertions.then;

//@RequiredArgsConstructor
//@SpringBootTest  // To load full application context
@DataJpaTest  // Test only data relevant area
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentRepositoryTest {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentRepositoryTest(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void getStudentByName() {

        // given
//        Student savedStudent = studentRepository.save(new Student("Soojeong"));
        Student savedStudent = entityManager.persistAndFlush(new Student("Soojeong"));

        // when
        Student student = studentRepository.getStudentByName("Soojeong");

        // then
        then(student.getId()).isNotNull();
        then(student.getName()).isEqualTo(savedStudent.getName());
    }
}