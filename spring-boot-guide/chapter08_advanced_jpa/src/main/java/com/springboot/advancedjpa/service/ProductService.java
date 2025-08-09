package com.springboot.advancedjpa.service;

import com.springboot.advancedjpa.dto.ProductDto;
import com.springboot.advancedjpa.dto.ProductResponseDto;

public interface ProductService {

    ProductResponseDto getProduct(Long number) throws Exception;

    ProductResponseDto saveProduct(ProductDto productDto);

    ProductResponseDto changeProductName(Long number, String name) throws Exception;

    void deleteProduct(Long number);
}
