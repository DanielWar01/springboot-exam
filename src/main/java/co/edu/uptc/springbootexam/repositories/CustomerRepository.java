package co.edu.uptc.springbootexam.repositories;

import co.edu.uptc.springbootexam.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
