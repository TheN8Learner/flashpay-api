package com.payflash.service;

import com.payflash.dto.ProductRequestDto;
import com.payflash.dto.ProductResponseDto;
import com.payflash.exception.ProductNotFoundException;
import com.payflash.model.Product;
import com.payflash.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<ProductResponseDto> getProducts(Pageable pageable){
        return productRepository.findAll(pageable)
                .map(product -> new ProductResponseDto(
                        product.getId(),
                        product.getName(),
                        product.getPrice(),
                        product.isAvailable()));
    }

    public ProductResponseDto createProduct(ProductRequestDto productRequestDto){
        Product product = new Product(productRequestDto.getName(), productRequestDto.getPrice());
        Product saved = productRepository.save(product);
        return new ProductResponseDto(saved.getId(), saved.getName(), saved.getPrice(), saved.isAvailable());
    }

    public ProductResponseDto getProduct(Long id){
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException(id)
        );
        return new ProductResponseDto(product.getId(), product.getName(), product.getPrice(), product.isAvailable());}

    public void deleteProduct(Long id){
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException(id)
        );
        productRepository.delete(product);
    }

    public ProductResponseDto modifyProduct(Long id, ProductRequestDto productRequestDto){
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException(id)
        );
        product.setName(productRequestDto.getName());
        product.setPrice(productRequestDto.getPrice());
        product.setAvailable(productRequestDto.isAvailable());
        Product saved = productRepository.save(product);
        return new ProductResponseDto(saved.getId(), saved.getName(), saved.getPrice(), saved.isAvailable()) ;
    }
}
