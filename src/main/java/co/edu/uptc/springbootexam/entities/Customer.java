package co.edu.uptc.springbootexam.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID del cliente generado automáticamente", hidden = true)
    private Long id;

    @Column(nullable = false)
    @Schema(description = "Nombre del cliente", example = "Cliente 1", required = true)
    private String name;

    @Column(nullable = false, unique = true)
    @Schema(description = "Correo electrónico del cliente", example = "cliente1@example.com", required = true)
    private String email;

    @Schema(description = "Teléfono del cliente", example = "123456789")
    private String phone;

    @Schema(description = "Dirección del cliente", example = "Dirección del Cliente 1")
    private String address;

    @Column(name = "created_at", updatable = false)
    @Schema(description = "Fecha de creación del cliente", hidden = true)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @Schema(description = "Fecha de actualización del cliente", hidden = true)
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
