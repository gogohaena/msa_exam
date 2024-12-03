package com.sparta.msa_exam.product.dto;

import com.sparta.msa_exam.product.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductResponse {
    private Long productId;
    private String name;
    private int supplyPrice;
    private String description;

    public ProductResponse (Product product) {
        this.productId = product.getProductId();
        this.name = product.getName();
        this.supplyPrice = product.getSupplyPrice();
        this.description = product.getDescription();
    }
}