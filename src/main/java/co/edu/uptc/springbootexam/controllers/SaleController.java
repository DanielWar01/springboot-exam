package co.edu.uptc.springbootexam.controllers;

import co.edu.uptc.springbootexam.entities.Sale;
import co.edu.uptc.springbootexam.services.SaleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    @Operation(summary = "Obtener todas las ventas", description = "Devuelve una lista de todas las ventas registradas en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de ventas obtenida correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<List<Sale>> getAllSales() {
        List<Sale> sales = saleService.getAllSales();
        return ResponseEntity.ok(sales);
    }

    @Operation(summary = "Obtener una venta por ID", description = "Devuelve los detalles de una venta específica basada en el ID proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venta encontrada"),
            @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Sale> getSaleById(
            @Parameter(description = "ID de la venta a obtener") @PathVariable Long id) {
        Optional<Sale> sale = saleService.getSaleById(id);
        return sale.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear una nueva venta", description = "Crea una nueva venta y la almacena en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Venta creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta, puede que el cuerpo de la solicitud no sea válido")
    })
    @PostMapping
    public ResponseEntity<Sale> createSale(@RequestBody Sale sale) {
        try {
            Sale savedSale = saleService.saveSale(sale);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedSale);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null); // En caso de error al actualizar stock
        }
    }

    @Operation(summary = "Actualizar una venta existente", description = "Actualiza los detalles de una venta existente basada en el ID proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venta actualizada correctamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta o error en la actualización"),
            @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Sale> updateSale(
            @Parameter(description = "ID de la venta a actualizar") @PathVariable Long id,
            @RequestBody Sale saleDetails) {
        try {
            Sale updatedSale = saleService.update(id, saleDetails);
            return ResponseEntity.ok(updatedSale);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null); // En caso de error al actualizar stock
        }
    }

    @Operation(summary = "Eliminar una venta", description = "Elimina una venta de la base de datos por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Venta eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(
            @Parameter(description = "ID de la venta a eliminar") @PathVariable Long id) {
        Optional<Sale> sale = saleService.getSaleById(id);
        if (sale.isEmpty()) {
            return ResponseEntity.notFound().build(); // Si no se encuentra la venta, retorna 404
        }
        saleService.deleteSale(id);
        return ResponseEntity.noContent().build();
    }
}
