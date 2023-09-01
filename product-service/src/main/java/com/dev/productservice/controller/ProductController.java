package com.dev.productservice.controller;

import com.dev.productservice.dto.OrderResponse;
import com.dev.productservice.dto.ProductDto;
import com.dev.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/")
    public ResponseEntity<String> createOrAdd(@RequestBody ProductDto productDto){
        String result = productService.createOrAdd(productDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/all-products")
    @Cacheable(value = "allProductsCache")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> productDtoList = productService.getAllProduct();
        return ResponseEntity.ok(productDtoList);
    }

    @GetMapping("/{id}")
    @Cacheable(value = "productById", key = "#id")
    public ResponseEntity<ProductDto> getProductById(@PathVariable long id){
        ProductDto productDto = productService.getProductById(id);
        return ResponseEntity.ok(productDto);
    }

    @GetMapping("/{id}/order")
    @Cacheable(value = "placeOrder", key = "#id")
    public ResponseEntity<Map<String, String>> placeOrder(@PathVariable long id){
        Map<String, String> map = productService.placeOrder(id);
        return ResponseEntity.ok(map);
    }

    @GetMapping("/{orderNumber}/order-item")
    @Cacheable(value = "productByOrderNumber", key = "#orderNumber")
    public ResponseEntity<OrderResponse> getProductByOrderNUmber(@PathVariable String orderNumber){
        return ResponseEntity.ok(productService.getProductByOrderNumber(orderNumber));
    }
}
