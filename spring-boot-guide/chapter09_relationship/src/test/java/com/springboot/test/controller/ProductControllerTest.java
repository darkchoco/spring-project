package com.springboot.test.controller;

import com.google.gson.Gson;
import com.springboot.test.dto.ProductDto;
import com.springboot.test.dto.ProductResponseDto;
import com.springboot.test.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)  // Mockito 확장 기능 활성화
public class ProductControllerTest {

    @Mock
    private ProductServiceImpl productService;

    @InjectMocks  // @Mock으로 생성된 목 객체를 주입받을 필드
    private ProductController productController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        // MockMvc를 수동으로 설정
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void getProduct() throws Exception {
        given(productService.getProduct(123L))
                .willReturn(new ProductResponseDto(123L, "빛과 소금", 20, 300));

        String productId = "123";
        mockMvc.perform(get("/product?number=" + productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.price").exists())
                .andExpect(jsonPath("$.stock").exists())
                .andDo(print());

        verify(productService).getProduct(123L);  // 지정된 메소드가 실행됐는지 검증
    }

    @Test
    @DisplayName("Product 데이터 생성 테스트")
    void createProduct() throws Exception {
        given(productService.saveProduct(new ProductDto("김광석", 14, 500)))
                .willReturn(new ProductResponseDto(12345L, "김광석", 14, 500));

        ProductDto productDto = ProductDto.builder()
                .name("김광석")
                .price(14)
                .stock(500)
                .build();

        Gson gson = new Gson();  // ObjectMapper를 사용해도 되지만 현업에서 많이 사용되는 Gson을 활용한다
        String content = gson.toJson(productDto);

        mockMvc.perform(post("/product").content(content).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.price").exists())
                .andExpect(jsonPath("$.stock").exists())
                .andDo(print());

        verify(productService).saveProduct(new ProductDto("김광석", 14, 500));
    }
}
