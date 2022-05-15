package br.com.cursoudemy.productapi.modules.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    @NotEmpty(message = "Product name cant be empty")
    private String name;

    @NotNull
    private Integer quantityAvailable;

    @NotNull
    private Integer categoryId;

    @NotNull
    private Integer supplierId;

}
