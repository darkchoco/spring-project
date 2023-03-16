package darkchoco.logging4basiccrudapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class BasicCrudApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicCrudApiApplication.class, args);
	}
}
