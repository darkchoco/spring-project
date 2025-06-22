package com.springboot.test.repository;

import com.springboot.test.config.JpaAuditingConfiguration;
import com.springboot.test.domain.Product;
import com.springboot.test.domain.ProductDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(JpaAuditingConfiguration.class)
class ProductDetailRepositoryTest {

    @Autowired
    ProductDetailRepository productDetailRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    public void saveAndReadTest() {
        Product givenProduct = new Product();
        givenProduct.setName("Goldberg Variations");
        givenProduct.setPrice(12);
        givenProduct.setStock(1000);

        productRepository.save(givenProduct);

        ProductDetail givenProductDetail = new ProductDetail();
        givenProductDetail.setProduct(givenProduct);
        givenProductDetail.setDescription("Description");

        productDetailRepository.save(givenProductDetail);

        ProductDetail foundProductDetail =
                productDetailRepository.findById(givenProductDetail.getId())
                        .orElseThrow(() -> new RuntimeException("ProductDetail not found"));
        System.out.println("savedProduct: " + foundProductDetail.getProduct());
        System.out.println("savedProductDetail: " + foundProductDetail);
    }
}