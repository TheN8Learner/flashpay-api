package com.payflash.controller;

import com.payflash.dto.ProductRequestDto;
import com.payflash.dto.ProductResponseDto;
import com.payflash.model.Product;
import com.payflash.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponseDto>> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(productService.getProducts(pageable));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@Valid @RequestBody ProductRequestDto productRequestDto){
        ProductResponseDto created = productService.createProduct(productRequestDto);
        return  ResponseEntity.status(201).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable Long id){
        ProductResponseDto product = productService.getProduct(id);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Product>> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return  ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ProductResponseDto modifyProduct(@PathVariable Long id, @RequestBody ProductRequestDto productRequestDto){
        ProductResponseDto productResponseDto = productService.modifyProduct(id, productRequestDto);
        return ResponseEntity.ok(productResponseDto).getBody();
    }
}
