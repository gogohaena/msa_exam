package com.sparta.msa_exam.product.dto;

import com.sparta.msa_exam.product.entity.Product;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductResponse {
    private final Long productId;
    private final String name;
    private final String description;
    private final int supplyPrice;

    @Builder
    public ProductResponse(Product product) {
        this.productId = product.getProductId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.supplyPrice = product.getSupplyPrice();
    }
}