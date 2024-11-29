package co.edu.uptc.springbootexam.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = false)
    @Schema(description = "Venta asociada al artículo de venta", required = true)
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @Schema(description = "Producto asociado al artículo de venta", required = true)
    private Product product;

    @Column(nullable = false)
    @Schema(description = "Cantidad de producto vendida", example = "2", required = true)
    private Integer quantity;

    @Column(nullable = false)
    @Schema(description = "Precio del artículo de venta", example = "100.0", required = true)
    private Double price;

    @Column(name = "created_at")
    @Schema(description = "Fecha de actualización del artículo de venta",  hidden = true)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
