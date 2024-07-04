package darkchoco.productmgt.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Product {

    // 도메인 객체에 대해서 setter를 쓰는 것은 피해야 하지만 id의 관리 책임을
    // ListProductRepository로 가져오면서 외부에서 id를 설정할 필요가 생겼다. 일종의 Trade-off 인 셈.
    @Setter
    private Long id;
    private String name;
    private Integer price;
    private Integer amount;
}
