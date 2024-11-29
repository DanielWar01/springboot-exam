package co.edu.uptc.springbootexam.repositories;

import co.edu.uptc.springbootexam.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
