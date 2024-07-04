package darkchoco.productmgt;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductMgtApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductMgtApplication.class, args);
    }

    @Bean
    public ModelMapper modelmapper() {
        ModelMapper modelMapper = new ModelMapper();

        // ModelMapper의 기본설정은 '매개변수 없는 생성자로 인스턴스 생성 후 setter로 값을 초기화하여 변환'
        // 하는 것이다.
        // setter 없이도 Product와 ProductDTO를 변환가능하게 하려면 아래와 같이 ModelMapper가
        // private 필드에 Reflection API로 접근하여 변환할 수 있도록 해야 한다.
        modelMapper.getConfiguration()
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setFieldMatchingEnabled(true);
        return modelMapper;
    }
}
