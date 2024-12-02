package com.sparta.msa_exam.product.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductRequest {

    @NotNull(message = "상품명을 입력해주세요.")
    private String name;

    private String description;

    @NotNull(message = "상품 가격을 입력해주세요.")
    private int supplyPrice;
}