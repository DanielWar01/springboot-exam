package co.edu.uptc.springbootexam.controllers;

import co.edu.uptc.springbootexam.entities.SaleItem;
import co.edu.uptc.springbootexam.services.SaleItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sale-items")
@RequiredArgsConstructor
public class SaleItemController {

    private final SaleItemService saleItemService;

    @Operation(summary = "Obtener todos los artículos de ventas", description = "Devuelve una lista de todos los artículos de venta registrados en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de artículos de venta obtenida correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<List<SaleItem>> getAllSaleItems() {
        List<SaleItem> saleItems = saleItemService.findAll();
        return ResponseEntity.ok(saleItems);
    }

    @Operation(summary = "Obtener un artículo de venta por ID", description = "Devuelve los detalles de un artículo de venta específico basado en el ID proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Artículo de venta encontrado"),
            @ApiResponse(responseCode = "404", description = "Artículo de venta no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SaleItem> getSaleItemById(
            @Parameter(description = "ID del artículo de venta a obtener") @PathVariable Long id) {
        Optional<SaleItem> saleItem = saleItemService.findById(id);
        return saleItem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo artículo de venta", description = "Crea un nuevo artículo de venta y lo almacena en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Artículo de venta creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    })
    @PostMapping
    public ResponseEntity<SaleItem> createSaleItem(@RequestBody SaleItem saleItem) {
        SaleItem savedSaleItem = saleItemService.save(saleItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSaleItem);
    }

    @Operation(summary = "Eliminar un artículo de venta", description = "Elimina un artículo de venta de la base de datos por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Artículo de venta eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Artículo de venta no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSaleItem(
            @Parameter(description = "ID del artículo de venta a eliminar") @PathVariable Long id) {
        saleItemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
