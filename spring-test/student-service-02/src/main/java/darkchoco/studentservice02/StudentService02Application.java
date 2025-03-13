package darkchoco.studentservice02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class StudentService02Application {

	public static void main(String[] args) {
		SpringApplication.run(StudentService02Application.class, args);
	}
}
