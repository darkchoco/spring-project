package darkchoco.productmgt.repository;

import darkchoco.productmgt.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ListProductRepository {

    // 웹 애플리케이션은 여러 개의 스레드가 동시에 동작하는 멀티 스레드 환경이기 때문에
    // 'Thread safe' 컬렉션을 사용해야 한다.
    // AtomicLong도 마찬가지로 Thread safe 클래스이다.
    private final List<Product> products = new CopyOnWriteArrayList<>();
    private final AtomicLong sequence = new AtomicLong(1L);

    public Product add(Product product) {
        product.setId(sequence.getAndAdd(1L));

        products.add(product);
        return product;
    }
}
