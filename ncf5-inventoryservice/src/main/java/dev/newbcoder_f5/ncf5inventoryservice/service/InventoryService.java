package dev.newbcoder_f5.ncf5inventoryservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.newbcoder_f5.ncf5inventoryservice.dto.InventoryResponse;
import dev.newbcoder_f5.ncf5inventoryservice.repository.InventoryRepository;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public List<InventoryResponse> isInStock(List<String> skuCodeList) {
        return inventoryRepository.findBySkuCodeIn(skuCodeList)
                        .stream()
                        .map(inventory ->
                            InventoryResponse.builder()
                                .skuCode(inventory.getSkuCode())
                                .isInStock(inventory.getQuantity() > 0)
                                .build()
                        )
                        .toList();
    }
    
}
