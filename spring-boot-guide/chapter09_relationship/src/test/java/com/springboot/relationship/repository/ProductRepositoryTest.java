package com.springboot.relationship.repository;

import com.springboot.relationship.config.JpaAuditingConfiguration;
import com.springboot.relationship.domain.Product;
import com.springboot.relationship.domain.Provider;
import com.springboot.relationship.repository.ProductRepository;
import com.springboot.relationship.repository.ProviderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * \@DataJpaTest:
 * - JPA 관련된 설정만 로드해서 테스트 진행
 * - @Transactional 을 기본적으로 포함하여 테스트 종료시 자동으로 Rollback 진행
 * - 기본값으로 임베디드 데이터베이스 사용
 * <p>
 * \@Import(JpaAuditingConfiguration.class):
 * - @DataJpaTest annotation doesn't automatically load @Configuration classes. You need to
 *   explicitly import the JpaAuditingConfiguration in your test class.
 */
@DataJpaTest
@Import(JpaAuditingConfiguration.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)  // NONE: 실제 DB, ANY: 임베디드 DB
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProviderRepository providerRepository;

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

    @Test
    void sortingAndPagingTest() {
        // given
        Product product1 = new Product();
        product1.setName("Atomic Heart");
        product1.setPrice(20);
        product1.setStock(100);
//        product1.setCreatedAt(LocalDateTime.now());
//        product1.setUpdatedAt(LocalDateTime.now());

        Product product2 = new Product();
        product2.setName("Atomic Heart");
        product2.setPrice(9);
        product2.setStock(1000);
//        product2.setCreatedAt(LocalDateTime.now());
//        product2.setUpdatedAt(LocalDateTime.now());

        Product product3 = new Product();
        product3.setName("Silent Hill");
        product3.setPrice(80);
        product3.setStock(500);
//        product3.setCreatedAt(LocalDateTime.now());
//        product3.setUpdatedAt(LocalDateTime.now());

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        System.out.println(productRepository.findByNameOrderByNumberAsc("Atomic Heart"));
        System.out.println(productRepository.findByNameOrderByNumberDesc("Atomic Heart"));

        System.out.println(productRepository.findByNameOrderByPriceAscStockDesc("Silent Hill"));

        // 예제 8.16
        System.out.println(productRepository.findByName("Silent Hill", Sort.by(Sort.Order.asc("price"))));
        System.out.println(productRepository.findByName("Silent Hill", Sort.by(Sort.Order.asc("price"), Sort.Order.desc("stock"))));
        // 예제 8.17
        System.out.println(productRepository.findByName("Silent Hill", getSort()));

        System.out.println(productRepository.findByName("Silent Hill", PageRequest.of(0, 2)));
        // 예제 8.19
        Page<Product> productPage = productRepository.findByName("Silent Hill", PageRequest.of(0, 2));
        // 예제 8.20
        System.out.println(productPage.getContent());

        System.out.println(productRepository.findByName("Silent Hill", PageRequest.of(0, 2, Sort.by(Sort.Order.asc("price")))));
        System.out.println(productRepository.findByName("Silent Hill", PageRequest.of(0, 2, Sort.by(Sort.Order.asc("price")))).getContent());
    }

    // 예제 8.17
    private Sort getSort() {
        return Sort.by(
                Sort.Order.asc("price"),
                Sort.Order.desc("stock")
        );
    }

    @Test
    public void auditingTest() {
        Product product = new Product();
        product.setName("Goldberg Variations");
        product.setPrice(12);
        product.setStock(1000);

        Product savedProduct = productRepository.save(product);

        System.out.println("Product Name: " + savedProduct.getName());
        System.out.println("createdAt: " + savedProduct.getCreatedAt());
    }

    @Test
    void relationshipTest() {
        Provider provider = new Provider();
        provider.setName("Deutsche Grammophon");

        providerRepository.save(provider);

        Product product = new Product();
        product.setName("Goldberg Variations");
        product.setPrice(12);
        product.setStock(1000);
        product.setProvider(provider);

        productRepository.save(product);

        System.out.println("Product: " + productRepository.findById(1L).orElseThrow(() ->
                new RuntimeException("Product not found")));
        System.out.println("Provider: " + productRepository.findById(1L).orElseThrow(() ->
                new RuntimeException("Product not found")).getProvider());
    }
}
