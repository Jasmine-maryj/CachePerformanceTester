package com.dev.productservice.controller;

import com.dev.productservice.dto.ProductDto;
import com.dev.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> productDtoList = productService.getAllProduct();
        return ResponseEntity.ok(productDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable long id){
        ProductDto productDto = productService.getProductById(id);
        return ResponseEntity.ok(productDto);
    }
}
