package com.payflash.controller;

import com.payflash.model.Product;
import com.payflash.service.ProductService;
import jakarta.validation.Valid;
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
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product){
        Product created = productService.createProduct(product.getName(), product.getPrice());
        return  ResponseEntity.status(201).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
        Product product = productService.getProduct(id);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Product>> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return  ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public Product modifyProduct(@PathVariable Long id, @RequestBody Product product){
        productService.modifyProduct(id, product.getName(), product.getPrice(), product.isAvailable());
        return ResponseEntity.ok(product).getBody();
    }
}
