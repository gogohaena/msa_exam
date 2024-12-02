package com.sparta.msa_exam.product.service;

import com.sparta.msa_exam.product.dto.ProductRequest;
import com.sparta.msa_exam.product.dto.ProductResponse;
import com.sparta.msa_exam.product.entity.Product;
import com.sparta.msa_exam.product.global.exception.BusinessException;
import com.sparta.msa_exam.product.global.exception.ErrorCode;
import com.sparta.msa_exam.product.repository.ProductRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final ProductRepository productRepository;

    @Transactional
    public ProductResponse createProduct(ProductRequest request) {

        Product product = Product.builder()
            .name(request.getName())
            .description(request.getDescription())
            .supplyPrice(request.getSupplyPrice())
            .build();

        Product savedProduct = productRepository.save(product);

        return new ProductResponse(savedProduct);
    }

    public List<ProductResponse> getProducts(Pageable pageable) {

        Page<Product> products = productRepository.findAll(pageable);

        return products.stream()
            .map(ProductResponse::new)
            .collect(Collectors.toList());
    }

    @CircuitBreaker(name = "productService", fallbackMethod = "fallbackGetProductDetails")
    public Optional<Product> getProductDetails(Long id) {

        Optional<Product> product = Optional.ofNullable(
			productRepository.findById(id).orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_NOT_FOUND)));

        return product;
    }

    public String fallbackGetProductDetails(Long id, Throwable t) {
        return  "Fallback Product : " + id;
    }
}