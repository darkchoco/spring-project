package com.springboot.relationship.service;

import com.springboot.relationship.dto.ProductDto;
import com.springboot.relationship.dto.ProductResponseDto;

public interface ProductService {

    ProductResponseDto getProduct(Long number) throws Exception;

    ProductResponseDto saveProduct(ProductDto productDto);

    ProductResponseDto changeProductName(Long number, String name) throws Exception;

    void deleteProduct(Long number);
}
