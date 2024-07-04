package darkchoco.productmgt.controller;

import darkchoco.productmgt.domain.Product;
import darkchoco.productmgt.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public Product createProduct(@RequestBody Product product) {
        // Spring Framework는 Json <--> 인스턴스 로 변환해주는 컨버터를 포함하고 있다.
        return productService.add(product);
    }
}
