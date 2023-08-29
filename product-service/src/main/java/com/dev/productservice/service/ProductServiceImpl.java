package com.dev.productservice.service;

import com.dev.productservice.dto.ProductDto;
import com.dev.productservice.entity.Product;
import com.dev.productservice.handler.CustomException;
import com.dev.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;
    @Override
    public String createOrAdd(ProductDto productDto) {
        try {
            Product product = new Product();
            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());
            productRepository.save(product);
            return "Added successfully!";
        }catch (Exception exception){
            throw new CustomException("Could not add or create the product!");
        }
    }

    @Override
    public List<ProductDto> getAllProduct() {
        List<Product> productList = productRepository.findAll();
        return productList.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(long id) {
        Product product = productRepository.findById(id).orElseThrow(()-> new CustomException("Product Not Found"));

        return ProductDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }

    private ProductDto mapToDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        return productDto;
    }
}
