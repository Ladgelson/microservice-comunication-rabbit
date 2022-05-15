package br.com.cursoudemy.productapi.modules.category.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CategoryRequest {

    @NotEmpty(message = "Description can't be null or blank.")
    private String description;

}
