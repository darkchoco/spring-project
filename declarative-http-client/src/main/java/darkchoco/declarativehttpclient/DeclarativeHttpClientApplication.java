package darkchoco.declarativehttpclient;

import darkchoco.declarativehttpclient.web.UserClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class DeclarativeHttpClientApplication implements CommandLineRunner {

    final UserClient userClient;

    public DeclarativeHttpClientApplication(UserClient userClient) {
        this.userClient = userClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(DeclarativeHttpClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        userClient.getAll().subscribe(
                d -> log.info("User: {}", d)
        );

        // 아래 결과가 위의 것보다 먼저 나올 수 있다. subscribe(), 즉 비동기방식임을 잊지 말자.
        userClient.getById(1L).subscribe(
                d -> log.info("User: {}", d)
        );
    }
}
