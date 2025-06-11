package com.springboot.test.service;

import com.springboot.test.dto.ProductDto;
import com.springboot.test.dto.ProductResponseDto;
import com.springboot.test.domain.Product;
import com.springboot.test.repository.ProductRepository;
import java.time.LocalDateTime;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDto getProduct(Long number) throws Exception {
        log.info("[getProduct] input number: {}", number);

        return productRepository.findById(number)
                .map(product -> {
                    ProductResponseDto productResponseDto = new ProductResponseDto();
                    productResponseDto.setNumber(product.getNumber());
                    productResponseDto.setName(product.getName());
                    productResponseDto.setPrice(product.getPrice());
                    productResponseDto.setStock(product.getStock());
                    return productResponseDto;
                })
                .orElseThrow(Exception::new);
    }

    @Override
    public ProductResponseDto saveProduct(ProductDto productDto) {
        log.info("[saveProduct] productDTO: {}", productDto.toString());

        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        Product savedProduct = productRepository.save(product);
        log.info("[saveProduct] savedProduct: {}", savedProduct);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(savedProduct.getNumber());
        productResponseDto.setName(savedProduct.getName());
        productResponseDto.setPrice(savedProduct.getPrice());
        productResponseDto.setStock(savedProduct.getStock());

        return productResponseDto;
    }

    @Override
    public ProductResponseDto changeProductName(Long number, String name) throws Exception {
        return productRepository.findById(number)
                .map(product -> {
                    product.setName(name);
                    Product changedProduct = productRepository.save(product);

                    ProductResponseDto productResponseDto = new ProductResponseDto();
                    productResponseDto.setNumber(changedProduct.getNumber());
                    productResponseDto.setName(changedProduct.getName());
                    productResponseDto.setPrice(changedProduct.getPrice());
                    productResponseDto.setStock(changedProduct.getStock());
                    return productResponseDto;
                })
                .orElseThrow(Exception::new);
    }

    @Override
    public void deleteProduct(Long number) {
        productRepository.deleteById(number);
    }
}
