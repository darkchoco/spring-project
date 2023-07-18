package darkchoco.data;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@RequiredArgsConstructor
@EnableScheduling
@Component
class PlaneFinderPoller {
    private final WebClient client =
            WebClient.create("http://localhost:7634/aircraft");

    private final RedisConnectionFactory connectionFactory;
    private final RedisOperations<String, Aircraft> redisOperations;

    @Scheduled(fixedRate = 1000)
    private void pollPlanes() {
        connectionFactory.getConnection().serverCommands().flushDb();

        client.get()
                .retrieve()
                .bodyToFlux(Aircraft.class)  // Flux로 변환
                .filter(plane -> !plane.getReg().isEmpty())  // 등록번호 없는 Aircraft 제외
                .toStream()
                .forEach(ac -> redisOperations.opsForValue().set(ac.getReg(), ac));

        Objects.requireNonNull(redisOperations.opsForValue()
                        .getOperations()
                        .keys("*"))
                .forEach(ac -> System.out.println(redisOperations.opsForValue().get(ac)));
    }
}
