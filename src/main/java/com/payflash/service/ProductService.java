package com.payflash.service;

import com.payflash.exception.ProductNotFoundException;
import com.payflash.model.Product;
import com.payflash.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public Product createProduct(String name, double Price){
        Product product = new Product(name, Price);
        return productRepository.save(product);
    }

    public Product getProduct(Long id){
        return productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException(id)
        );}

    public void deleteProduct(Long id){
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException(id)
        );
        productRepository.delete(product);
    }

    public Product modifyProduct(Long id, String name, double price, boolean available){
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException(id)
        );
        product.setName(name);
        product.setPrice(price);
        product.setAvailable(available);
        return  productRepository.save(product);
    }
}
