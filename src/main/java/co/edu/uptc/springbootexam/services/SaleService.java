package co.edu.uptc.springbootexam.services;

import co.edu.uptc.springbootexam.entities.Product;
import co.edu.uptc.springbootexam.entities.Sale;
import co.edu.uptc.springbootexam.entities.SaleItem;
import co.edu.uptc.springbootexam.repositories.ProductRepository;
import co.edu.uptc.springbootexam.repositories.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleService {
    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;

    public Sale saveSale(Sale sale) {
        Sale savedSale = sale;
        double total = 0;

        // Ahora actualizamos el stock de los productos relacionados en la venta
        for (SaleItem item : savedSale.getItems()) {
            Product product = item.getProduct();
            int quantitySold = item.getQuantity();
            total += item.getQuantity()*item.getPrice();

            // Verificamos si hay suficiente stock
            if (product.getStock() >= quantitySold) {
                // Restamos la cantidad vendida del stock
                product.setStock(product.getStock() - quantitySold);
                productRepository.save(product);  // Guardamos el producto actualizado
            } else {
                // Si no hay suficiente stock, puedes lanzar una excepción o manejar el error
                throw new RuntimeException("Not enough stock for product: " + product.getName());
            }
        }
        savedSale.setTotal(total);
        return saleRepository.save(savedSale);
    }

    public Optional<Sale> getSaleById(Long id) {
        return saleRepository.findById(id);
    }

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public void deleteSale(Long id) {
        saleRepository.deleteById(id);
    }

    public Sale update(Long id, Sale saleDetails) {
        Optional<Sale> existingSale = saleRepository.findById(id);
        if (existingSale.isPresent()) {
            Sale sale = existingSale.get();
            // Actualiza los campos necesarios
            sale.setSaleDate(saleDetails.getSaleDate());
            sale.setTotal(saleDetails.getTotal());
            sale.setUpdatedAt(LocalDateTime.now());  // Actualiza el campo de fecha
            return saleRepository.save(sale);
        } else {
            throw new RuntimeException("Sale not found with id " + id);  // Puedes lanzar una excepción personalizada o retornar un valor adecuado.
        }
    }
}
