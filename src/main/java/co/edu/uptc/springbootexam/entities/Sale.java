package co.edu.uptc.springbootexam.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnore
    @Schema(description = "Cliente asociado a la venta", required = true)
    private Customer customer;

    @Column(name = "sale_date")
    @Schema(description = "Fecha y hora de la venta", example = "2024-11-28T12:34:56", required = true)
    private LocalDateTime saleDate;

    @Schema(description = "Monto total de la venta", example = "200.0", required = true)
    private Double total;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private List<SaleItem> items;

    @Column(name = "created_at")
    @Schema(description = "Fecha de creación del venta",  hidden = true)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @Schema(description = "Fecha de actualización del venta",  hidden = true)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        saleDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}