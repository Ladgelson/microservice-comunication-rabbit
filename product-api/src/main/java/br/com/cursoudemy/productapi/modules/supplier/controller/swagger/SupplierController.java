package br.com.cursoudemy.productapi.modules.supplier.controller.swagger;

import br.com.cursoudemy.productapi.modules.supplier.dto.SupplierRequest;
import br.com.cursoudemy.productapi.modules.supplier.dto.SupplierResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Supplier", description = "Resource to manage data about supplier")
@RequestMapping("/v1/suppliers")
public interface SupplierController {
    @Operation(description = "Create a new supplier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Supplier successfully saved", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SupplierResponse.class)
                    )}),
            @ApiResponse(responseCode = "400", description = "Missing or invalid request body", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)})
    @PostMapping
    ResponseEntity<SupplierResponse> saveSupplier(@RequestBody @Validated SupplierRequest supplierRequest);
}
