package co.edu.uptc.springbootexam.repositories;

import co.edu.uptc.springbootexam.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
}
