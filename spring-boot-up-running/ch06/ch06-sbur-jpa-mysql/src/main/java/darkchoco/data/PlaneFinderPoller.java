package darkchoco.data;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@EnableScheduling
@Component
class PlaneFinderPoller {
    private final WebClient client =
            WebClient.create("http://localhost:7634/aircraft");

    private final AircraftRepository repository;

    @Scheduled(fixedRate = 1000)
    private void pollPlanes() {
//        repository.deleteAll();

        client.get()
                .retrieve()
                .bodyToFlux(Aircraft.class)  // Flux로 변환
                .filter(plane -> !plane.getReg().isEmpty())  // 등록번호 없는 Aircraft 제외
                .toStream()
                .forEach(repository::save);

        repository.findAll().forEach(System.out::println);
    }
}
