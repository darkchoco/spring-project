package darkchoco.studentservice03;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Component
public class StudentClient {

    private final WebClient webClient;

    public Student getStudent(Long id) {
        return webClient.get()
                .uri("/students/{id}", id)
                .retrieve()
                .bodyToMono(Student.class)
                .block();
    }
}
