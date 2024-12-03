package com.sparta.msa_exam.product.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductRequest {

    @NotNull
    private String name;

    private String description;

    @NotNull
    private int supplyPrice;
}