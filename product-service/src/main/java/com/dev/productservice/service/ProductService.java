package com.dev.productservice.service;

import com.dev.productservice.dto.ProductDto;

import java.util.List;

public interface ProductService {
    String createOrAdd(ProductDto productDto);

    List<ProductDto> getAllProduct();

    ProductDto getProductById(long id);
}
