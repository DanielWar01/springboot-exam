package co.edu.uptc.springbootexam.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sales")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID de la venta generado automáticamente", hidden = true)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    @Schema(description = "Cliente asociado a la venta", required = true)
    private Customer customer;

    @Column(name = "sale_date", nullable = false)
    @Schema(description = "Fecha y hora de la venta", example = "2024-11-28T12:34:56", required = true)
    private LocalDateTime saleDate;

    @Column(nullable = false)
    @Schema(description = "Monto total de la venta", example = "200.0", required = true)
    private Double total;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "Lista de artículos de la venta", hidden = true)
    private List<SaleItem> items;

    @Column(name = "created_at", updatable = false)
    @Schema(description = "Fecha de creación de la venta", hidden = true)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @Schema(description = "Fecha de actualización de la venta", hidden = true)
    private LocalDateTime updatedAt;

    @PrePersist
    private void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (saleDate == null) {
            saleDate = LocalDateTime.now();
        }
    }

    @PreUpdate
    private void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
