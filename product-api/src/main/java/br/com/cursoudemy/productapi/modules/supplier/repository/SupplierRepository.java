package br.com.cursoudemy.productapi.modules.supplier.repository;

import br.com.cursoudemy.productapi.modules.supplier.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}
