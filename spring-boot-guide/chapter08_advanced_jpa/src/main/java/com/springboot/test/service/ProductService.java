package com.springboot.test.service;

import com.springboot.test.dto.ProductDto;
import com.springboot.test.dto.ProductResponseDto;

public interface ProductService {

    ProductResponseDto getProduct(Long number) throws Exception;

    ProductResponseDto saveProduct(ProductDto productDto);

    ProductResponseDto changeProductName(Long number, String name) throws Exception;

    void deleteProduct(Long number);
}
