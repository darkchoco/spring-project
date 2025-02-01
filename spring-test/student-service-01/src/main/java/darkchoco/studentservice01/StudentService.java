package darkchoco.studentservice01;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Cacheable("students")
    public Student getStudentByName(String name) {
        return studentRepository.getStudentByName(name);
    }
}
