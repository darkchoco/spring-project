package darkchoco.studentservice01;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student getStudentByName(String name);

//    @Query("SELECT avg(grade) FROM sts_student WHERE active=true ")
//    Double getAvgGradeForActiveStudents();
}
