package darkchoco.declarativehttpclient.config;

import darkchoco.declarativehttpclient.web.UserClient;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebConfig {

    // 기본 URI를 갖는 WebClient Bean을 생성하고 필요한 곳에서 갖다가 주입하는 방식이 유용하다.
    @Bean
    WebClient webClient() {
        return WebClient.builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build();
    }

    @SneakyThrows
    @Bean
    UserClient postClient(WebClient webClient) {
        HttpServiceProxyFactory httpServiceProxyFactory =
                HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient))
                        .build();
        return httpServiceProxyFactory.createClient(UserClient.class);
    }
}
