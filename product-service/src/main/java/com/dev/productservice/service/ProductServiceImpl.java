package com.dev.productservice.service;

import com.dev.productservice.dto.OrderResponse;
import com.dev.productservice.dto.ProductDto;
import com.dev.productservice.entity.Product;
import com.dev.productservice.handler.CustomException;
import com.dev.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService{

    private static final String ORDER_SERVICE = "http://localhost:8080/api/v1/orders";

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;
    @Override
    public String createOrAdd(ProductDto productDto) {
        try {
            Product product = new Product();
            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());
            product.setQuantity(productDto.getQuantity());
            productRepository.save(product);
            return "Added successfully!";
        }catch (Exception exception){
            throw new CustomException("Could not add or create the product!");
        }
    }

    @Override
    public List<ProductDto> getAllProduct() {
        List<Product> productList = productRepository.findAll();
        return productList.stream().map(this::mapToDto).toList();
    }

    @Override
    public ProductDto getProductById(long id) {
        Product product = productRepository.findById(id).orElseThrow(()-> new CustomException("Product Not Found"));

        return ProductDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }

    @Override
    public Map<String, String> placeOrder(long id) {
        ProductDto product = this.getProductById(id);
        log.info("product " + product);
        try{
            Map<String, String> result = placeOrder(product);
            return result;
        }catch (Exception exception){
            throw  new CustomException("Could not place order, please try again later");
        }
    }

    @Override
    public OrderResponse getProductByOrderNumber(String orderNumber) {
        try {
            OrderResponse orderResponse = getProductByOrderNumberFromOS(orderNumber);
            if(orderResponse == null){
                throw new CustomException("Order not found");
            }
            return orderResponse;

        }catch (Exception exception){
            throw new CustomException("Could not fetch the order details 2");
        }
    }

    private OrderResponse getProductByOrderNumberFromOS(String orderNumber) {
        WebClient webClient = webClientBuilder.baseUrl(ORDER_SERVICE).build();

        OrderResponse orderResponse = webClient.get()
                .uri(ORDER_SERVICE+"/"+orderNumber)
                .retrieve()
                .bodyToMono(OrderResponse.class)
                .block();
        if(orderResponse == null){
            throw new CustomException("could not fetch order details");
        }

        return orderResponse;
    }

    private ProductDto mapToDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setQuantity(product.getQuantity());
        return productDto;
    }

    private Map<String, String> placeOrder(ProductDto productDto){
        Map<String, String> map = new HashMap<>();

        WebClient webClient = webClientBuilder.baseUrl(ORDER_SERVICE).build();

        String orderNumber = webClient
                .post()
                .uri(ORDER_SERVICE+"/")
                .body(BodyInserters.fromValue(productDto))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        map.put("orderNumber", orderNumber);
        return map;
    }
}
