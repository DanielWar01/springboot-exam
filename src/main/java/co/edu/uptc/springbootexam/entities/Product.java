package co.edu.uptc.springbootexam.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID del producto generado automáticamente", hidden = true)
    private Long id;

    @Column(nullable = false)
    @Schema(description = "Nombre del producto", example = "Producto A", required = true)
    private String name;

    @Schema(description = "Descripción del producto", example = "Descripción detallada del Producto A")
    private String description;

    @Column(nullable = false)
    @Schema(description = "Precio del producto", example = "100.0", required = true)
    private Double price;

    @Column(nullable = false)
    @Schema(description = "Cantidad en stock del producto", example = "50", required = true)
    private Integer stock;

    @Column(name = "created_at", updatable = false)
    @Schema(description = "Fecha de creación del producto", hidden = true)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @Schema(description = "Fecha de actualización del producto", hidden = true)
    private LocalDateTime updatedAt;

    @PrePersist
    private void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    private void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
