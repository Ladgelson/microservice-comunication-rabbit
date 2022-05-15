package br.com.cursoudemy.productapi.modules.category.controller.impl;

import br.com.cursoudemy.productapi.modules.category.controller.swagger.CategoryController;
import br.com.cursoudemy.productapi.modules.category.dto.CategoryRequest;
import br.com.cursoudemy.productapi.modules.category.dto.CategoryResponse;
import br.com.cursoudemy.productapi.modules.category.model.Category;
import br.com.cursoudemy.productapi.modules.category.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@AllArgsConstructor
public class CategoryControllerImpl implements CategoryController {

    private final CategoryService categoryService;

    @Override
    public ResponseEntity<CategoryResponse> saveCategory(@Validated CategoryRequest categoryRequest) {
        CategoryResponse categoryResponse = categoryService.save(categoryRequest);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoryResponse.getId())
                .toUri();
        return ResponseEntity.created(uri).body(categoryResponse);
    }

    @Override
    public ResponseEntity<CategoryResponse> findById(Integer id) {
        CategoryResponse categoryResponse = CategoryResponse.of(categoryService.findById(id));
        return ResponseEntity.ok(categoryResponse);
    }

    @Override
    public ResponseEntity<Page<CategoryResponse>> findCategories(Pageable pageable) {
        Page<Category> categories = categoryService.findAll(pageable);
        if(categories.isEmpty()) return ResponseEntity.noContent().build();
        Page<CategoryResponse> categoryResponses = categories
                .map(CategoryResponse::of);
        return ResponseEntity.ok(categoryResponses);
    }
}
