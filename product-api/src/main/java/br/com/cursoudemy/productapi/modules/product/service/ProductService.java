package br.com.cursoudemy.productapi.modules.product.service;

import br.com.cursoudemy.productapi.modules.category.model.Category;
import br.com.cursoudemy.productapi.modules.category.service.CategoryService;
import br.com.cursoudemy.productapi.modules.product.dto.ProductRequest;
import br.com.cursoudemy.productapi.modules.product.dto.ProductResponse;
import br.com.cursoudemy.productapi.modules.product.dto.ProductStockDTO;
import br.com.cursoudemy.productapi.modules.product.model.Product;
import br.com.cursoudemy.productapi.modules.product.repository.ProductRepository;
import br.com.cursoudemy.productapi.modules.supplier.model.Supplier;
import br.com.cursoudemy.productapi.modules.supplier.service.SupplierService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final SupplierService supplierService;

    private final CategoryService categoryService;

    public ProductResponse save(ProductRequest productRequest) {
        Supplier supplier = supplierService.findById(productRequest.getSupplierId());
        Category category = categoryService.findById(productRequest.getCategoryId());
        Product product = Product.of(productRequest, supplier, category);
        product = productRepository.save(product);
        return ProductResponse.of(product);
    }

    public void updateProductStock(ProductStockDTO productStockDTO) {
    }
}
