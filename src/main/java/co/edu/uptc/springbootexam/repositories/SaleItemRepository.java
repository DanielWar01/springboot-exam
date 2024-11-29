package co.edu.uptc.springbootexam.repositories;

import co.edu.uptc.springbootexam.entities.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {
}
