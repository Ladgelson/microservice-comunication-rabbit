package br.com.cursoudemy.productapi.modules.supplier.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SupplierRequest {

    @NotBlank(message = "Description can't be null or blank.")
    private String description;

}
