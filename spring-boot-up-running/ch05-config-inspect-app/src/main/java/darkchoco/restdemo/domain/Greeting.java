package darkchoco.restdemo.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "greeting")
public class Greeting {

    private String name;
    private String coffee;
}
