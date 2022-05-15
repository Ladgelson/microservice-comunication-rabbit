package br.com.cursoudemy.productapi.modules.supplier.service;


import br.com.cursoudemy.productapi.config.exception.ValidateRequestException;
import br.com.cursoudemy.productapi.modules.supplier.dto.SupplierRequest;
import br.com.cursoudemy.productapi.modules.supplier.dto.SupplierResponse;
import br.com.cursoudemy.productapi.modules.supplier.model.Supplier;
import br.com.cursoudemy.productapi.modules.supplier.repository.SupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierResponse save(SupplierRequest request) {
        Supplier supplier = supplierRepository.save(Supplier.of(request));
        return SupplierResponse.of(supplier);
    }

    public Supplier findById(Integer id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new ValidateRequestException("Supplier id not found"));
    }
}
