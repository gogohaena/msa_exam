package com.sparta.msa_exam.product.entity;

import com.sparta.msa_exam.product.dto.ProductRequest;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false)
    private String name; // 상품명

    @Column
    private String description;

    @Column(nullable = false)
    private int supplyPrice;

    @Builder
    public Product(String name, String description, int supplyPrice) {
        this.name = name;
        this.description = description;
        this.supplyPrice = supplyPrice;
    }

    public void update(ProductRequest request) {
        this.name = request.getName();
        this.description = request.getDescription();
        this.supplyPrice = request.getSupplyPrice();
    }
}
