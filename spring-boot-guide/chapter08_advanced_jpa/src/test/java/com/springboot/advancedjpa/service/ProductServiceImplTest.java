package com.springboot.advancedjpa.service;

import com.springboot.advancedjpa.domain.Product;
import com.springboot.advancedjpa.dto.ProductDto;
import com.springboot.advancedjpa.dto.ProductResponseDto;
import com.springboot.advancedjpa.repository.ProductRepository;
import com.springboot.advancedjpa.service.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

class ProductServiceTest {

    private final ProductRepository productRepository = Mockito.mock(ProductRepository.class);
    private ProductServiceImpl productService;

    @BeforeEach
    public void setUpTest() {
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    void getProduct() throws Exception {
        Product givenProduct = new Product();
        givenProduct.setNumber(123L);
        givenProduct.setName("Goldberg Variations");
        givenProduct.setPrice(12);
        givenProduct.setStock(1000);

        Mockito.when(productRepository.findById(123L))
                .thenReturn(Optional.of(givenProduct));

        ProductResponseDto productResponseDto = productService.getProduct(123L);

        Assertions.assertEquals(productResponseDto.getNumber(), givenProduct.getNumber());
        Assertions.assertEquals(productResponseDto.getName(), givenProduct.getName());
        Assertions.assertEquals(productResponseDto.getPrice(), givenProduct.getPrice());
        Assertions.assertEquals(productResponseDto.getStock(), givenProduct.getStock());

        verify(productRepository).findById(123L);
    }

    /**
     * any() : ArgumentMatchers.any() 에 주목하기. Mock 객체인 ProductRepository의 동작 정의시 특정 매개변수의 전달을
     * 설정하지 않고 메서드 실행만 확인하거나 좀 더 큰 범위의 클래스 객체를 매개변수로 전달받는 등의 상황에 사용한다.
     */
    @Test
    void saveProduct() {
        Mockito.when(productRepository.save(any(Product.class)))
                .then(returnsFirstArg());

        ProductResponseDto productResponseDto =
                productService.saveProduct(new ProductDto("Goldberg Variations", 12, 1000));

        Assertions.assertEquals("Goldberg Variations", productResponseDto.getName());
        Assertions.assertEquals(12, productResponseDto.getPrice());
        Assertions.assertEquals(1000, productResponseDto.getStock());

        verify(productRepository).save(any());
    }
}