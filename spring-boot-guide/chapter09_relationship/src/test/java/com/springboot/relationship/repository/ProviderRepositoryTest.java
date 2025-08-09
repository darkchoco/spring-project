package com.springboot.relationship.repository;

import com.springboot.relationship.domain.Product;
import com.springboot.relationship.domain.Provider;
import com.springboot.relationship.repository.ProductRepository;
import com.springboot.relationship.repository.ProviderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
class ProviderRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Test
    void relationshipTest() {
        Provider provider = new Provider();
        provider.setName("Steam");

        providerRepository.save(provider);

        Product product1 = new Product();
        product1.setName("Atomic Heart");
        product1.setPrice(20);
        product1.setStock(100);

        Product product2 = new Product();
        product2.setName("Atomic Heart");
        product2.setPrice(9);
        product2.setStock(1000);

        Product product3 = new Product();
        product3.setName("Silent Hill");
        product3.setPrice(80);
        product3.setStock(500);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        List<Product> products = providerRepository.findById(provider.getId()).orElseThrow(() ->
                new RuntimeException("Provider not found")).getProductList();
        for (Product p : products)
            System.out.println(p);
    }
}