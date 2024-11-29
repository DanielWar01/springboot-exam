package co.edu.uptc.springbootexam.services;

import co.edu.uptc.springbootexam.entities.SaleItem;
import co.edu.uptc.springbootexam.repositories.SaleItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleItemService {
    private final SaleItemRepository saleItemRepository;

    public List<SaleItem> findAll() {
        return saleItemRepository.findAll();
    }

    public Optional<SaleItem> findById(Long id) {
        return saleItemRepository.findById(id);
    }

    public SaleItem save(SaleItem saleItem) {
        return saleItemRepository.save(saleItem);
    }

    public void deleteById(Long id) {
        saleItemRepository.deleteById(id);
    }

    public SaleItem update(Long id, SaleItem saleItemDetails) {
        Optional<SaleItem> existingSaleItem = saleItemRepository.findById(id);
        if (existingSaleItem.isPresent()) {
            SaleItem saleItem = existingSaleItem.get();
            // Actualiza los campos necesarios
            saleItem.setQuantity(saleItemDetails.getQuantity());
            saleItem.setPrice(saleItemDetails.getPrice());
            return saleItemRepository.save(saleItem);
        } else {
            throw new RuntimeException("SaleItem not found with id " + id);  // Puedes lanzar una excepción personalizada o retornar un valor adecuado.
        }
    }
}
