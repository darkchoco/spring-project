package darkchoco.productmgt.service;

import darkchoco.productmgt.domain.Product;
import darkchoco.productmgt.dto.ProductDTO;
import darkchoco.productmgt.repository.ListProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ListProductRepository listProductRepository;
    private final ModelMapper modelMapper;

    public ProductDTO add(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        Product savedProduct = listProductRepository.add(product);

        return modelMapper.map(savedProduct, ProductDTO.class);
    }
}
