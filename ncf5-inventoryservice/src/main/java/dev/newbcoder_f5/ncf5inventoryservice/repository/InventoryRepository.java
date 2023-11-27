package dev.newbcoder_f5.ncf5inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.newbcoder_f5.ncf5inventoryservice.model.Inventory;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    List<Inventory> findBySkuCodeIn(List<String> skuCode);
    
}
