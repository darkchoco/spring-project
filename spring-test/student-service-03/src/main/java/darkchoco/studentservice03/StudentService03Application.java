package darkchoco.studentservice03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class StudentService03Application {

    @Bean
    WebClient getWebClient(WebClient.Builder builder) {
        return builder.baseUrl("http://localhost:8080").build();
    }

    public static void main(String[] args) {
		SpringApplication.run(StudentService03Application.class, args);
	}
}
