package com.springboot.relationship.repository;

import com.springboot.relationship.domain.Category;
import com.springboot.relationship.domain.Product;
import com.springboot.relationship.repository.CategoryRepository;
import com.springboot.relationship.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("카테고리 코드로 조회 테스트")
    void findByCodeTest() {
        // given
        String categoryCode = "CAT1";
        Category category = Category.builder()
                .code(categoryCode)
                .name("전자제품")
                .build();
        categoryRepository.save(category);

        // when
        Category foundCategory = categoryRepository.findByCode(categoryCode);

        // then
        assertNotNull(foundCategory);
        assertEquals(categoryCode, foundCategory.getCode());
        assertEquals("전자제품", foundCategory.getName());
    }

    @Test
    @DisplayName("카테고리 코드 존재 여부 확인 테스트 - 존재하는 경우")
    void existsByCode_WhenExists_ReturnsTrue() {
        // given
        String categoryCode = "CAT2";
        Category category = Category.builder()
                .code(categoryCode)
                .name("의류")
                .build();
        categoryRepository.save(category);

        // when
        boolean exists = categoryRepository.existsByCode(categoryCode);

        // then
        assertTrue(exists);
    }

    @Test
    @DisplayName("카테고리 코드 존재 여부 확인 테스트 - 존재하지 않는 경우")
    void existsByCode_WhenNotExists_ReturnsFalse() {
        // given
        String nonExistentCode = "NON_EXISTENT";

        // when
        boolean exists = categoryRepository.existsByCode(nonExistentCode);

        // then
        assertFalse(exists);
    }

    @Test
    @DisplayName("relationship 테스트")
    void relationshipTest() {
        // 테스트 데이터 생성
        Product givenProduct = new Product();
        givenProduct.setName("Goldberg Variations");
        givenProduct.setPrice(12);
        givenProduct.setStock(1000);

        productRepository.save(givenProduct);

        String categoryCode = "Classical";
        Category category = Category.builder()
                .code(categoryCode)
                .name("Classical Music")
                .products(List.of(givenProduct))
                .build();

        categoryRepository.save(category);

        // 테스트
        List<Product> products = categoryRepository.findByCode(categoryCode).getProducts();

        for (Product p : products)
            System.out.println(p);
    }
}
