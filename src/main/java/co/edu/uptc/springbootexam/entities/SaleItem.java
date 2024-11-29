package co.edu.uptc.springbootexam.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "sale_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID del artículo de venta generado automáticamente", hidden = true)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sale_id", nullable = false)
    @Schema(description = "Venta asociada al artículo de venta", required = true)
    private Sale sale;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @Schema(description = "Producto asociado al artículo de venta", required = true)
    private Product product;

    @Column(nullable = false)
    @Schema(description = "Cantidad de producto vendida", example = "2", required = true)
    private Integer quantity;

    @Column(nullable = false)
    @Schema(description = "Precio del artículo de venta", example = "100.0", required = true)
    private Double price;

    @Column(name = "created_at", updatable = false)
    @Schema(description = "Fecha de creación del artículo de venta", hidden = true)
    private LocalDateTime createdAt;

    @PrePersist
    private void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
