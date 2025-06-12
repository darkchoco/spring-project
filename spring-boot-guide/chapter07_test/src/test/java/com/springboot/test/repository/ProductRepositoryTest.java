package com.springboot.test.repository;

import com.springboot.test.domain.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 * \@DataJpaTest:
 *  - JPA 관련된 설정만 로드해서 테스트 진행
 *  - @Transactional 을 기본적으로 포함하여 테스트 종료시 자동으로 Rollback 진행
 *  - 기본값으로 임베디드 데이터베이스 사용
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)  // NONE: 실제 DB, ANY: 임베디드 DB
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveTest() {
        // given
        Product givenProduct = new Product();
        givenProduct.setName("Goldberg Variations");
        givenProduct.setPrice(12);
        givenProduct.setStock(1000);

        // when
        Product savedProduct = productRepository.save(givenProduct);

        // then
        Assertions.assertNotNull(savedProduct.getNumber());  // ID가 생성되었는지 확인
        Assertions.assertEquals(savedProduct.getName(), givenProduct.getName());
        Assertions.assertEquals(savedProduct.getPrice(), givenProduct.getPrice());
        Assertions.assertEquals(savedProduct.getStock(), givenProduct.getStock());
    }

    @Test
    void selectTest() {
        // given
        Product givenProduct = new Product();
        givenProduct.setName("Goldberg Variations");
        givenProduct.setPrice(12);
        givenProduct.setStock(1000);

        Product savedProduct = productRepository.saveAndFlush(givenProduct);

        // when
        Product selectedProduct = productRepository.findById(savedProduct.getNumber())
                .orElseGet(() -> Assertions.fail("Product not found"));

        // then
        Assertions.assertEquals(selectedProduct.getName(), givenProduct.getName());
        Assertions.assertEquals(selectedProduct.getPrice(), givenProduct.getPrice());
        Assertions.assertEquals(selectedProduct.getStock(), givenProduct.getStock());
    }
}