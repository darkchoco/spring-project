package darkchoco.studentservice01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class StudentService01Application {

	public static void main(String[] args) {
		SpringApplication.run(StudentService01Application.class, args);
	}
}
