package darkchoco.restdemo;

import darkchoco.restdemo.domain.Coffee;
import darkchoco.restdemo.domain.CoffeeRepository;
import darkchoco.restdemo.domain.Droid;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@ConfigurationPropertiesScan
@SpringBootApplication
public class RestDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestDemoApplication.class, args);
	}

    @ConfigurationProperties(prefix = "droid")
    @Bean
    Droid createDroid() {
        return new Droid();
    }
}

@RequiredArgsConstructor
@Component
class DataLoader {

	private final CoffeeRepository coffeeRepository;

	@PostConstruct
	private void loadData() {
		coffeeRepository.saveAll(List.of(
				new Coffee("Café Cereza"),
				new Coffee("Café Ganador"),
				new Coffee("Café Lareño"),
				new Coffee("Café Três Pontas")
		));
	}
}
