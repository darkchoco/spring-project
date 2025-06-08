package darkchoco.contentservice.config;

import darkchoco.contentservice.service.ArticleClient;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class ClientConfig {

    /**
     * Spring Framework 6부터 blockTimeout은 HttpServiceProxyFactory에서 deprecated 되었다. 이는 Reactor 기반의
     * 비동기 호출에서는 블로킹 타임아웃을 직접 설정하는 것이 일반적이지 않으며, 대신 WebClient 자체에서
     * 타임아웃 설정을 관리하도록 권장하기 때문이다.
     * blockTimeout을 대체하는 방법은 다음과 같이 WebClient의 설정을 통해 타임아웃을 구성하는 것이다.
     * WebClient는 HTTP 클라이언트 라이브러리 (기본적으로 Reactor Netty)를 사용하여 타임아웃을 설정할 수 있다.
     */
    @Bean
    ArticleClient articleClient() {
        HttpClient httpClient = HttpClient.create()
                .responseTimeout(Duration.ofSeconds(7))  // 응답 전체 타임아웃 7초 설정
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)  // 연결 타임아웃 5초
                .doOnConnected(conn -> conn
                        .addHandlerLast(new ReadTimeoutHandler(7, TimeUnit.SECONDS))  // 읽기 타임아웃 7초
                        .addHandlerLast(new WriteTimeoutHandler(7, TimeUnit.SECONDS)));  // 쓰기 타임아웃 7초
        WebClient client = WebClient.builder()
                .baseUrl("http://localhost:8081/api")
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(client))
//                .blockTimeout(Duration.ofSeconds(7))
                .build();
        return factory.createClient(ArticleClient.class);
    }
}
