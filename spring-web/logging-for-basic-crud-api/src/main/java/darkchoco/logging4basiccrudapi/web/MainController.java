package darkchoco.logging4basiccrudapi.web;

import darkchoco.logging4basiccrudapi.domain.Student;
import darkchoco.logging4basiccrudapi.domain.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/student")
@Transactional(Transactional.TxType.SUPPORTS)
public class MainController {

    private final StudentRepository repository;

    @GetMapping
    public List<Student> getStudents() {
        return repository.findAll();
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return repository.save(student);
    }

    @PutMapping
    @Transactional(rollbackOn = Exception.class)
    public Student updateStudentAddress(@RequestParam("address") String address, @RequestParam("id") Integer id) {
        repository.updateAddressById(address, id);
        return repository.findById(id).orElse(null);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteStudent(@RequestParam("id") Integer id) {
        repository.deleteById(id);
        boolean studentExists = repository.existsById(id);
        if (!studentExists)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
