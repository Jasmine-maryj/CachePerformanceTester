package com.dev.productservice.service;

import com.dev.productservice.dto.OrderResponse;
import com.dev.productservice.dto.ProductDto;

import java.util.List;
import java.util.Map;

public interface ProductService {
    String createOrAdd(ProductDto productDto);

    List<ProductDto> getAllProduct();

    ProductDto getProductById(long id);

    Map<String, String> placeOrder(long id);

    OrderResponse getProductByOrderNumber(String orderNumber);
}
