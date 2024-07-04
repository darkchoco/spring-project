package darkchoco.productmgt.service;

import darkchoco.productmgt.domain.Product;
import darkchoco.productmgt.repository.ListProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ListProductRepository listProductRepository;

    public Product add(Product product) {
        return listProductRepository.add(product);
    }
}
