package dev.newbcoder_f5.ncf5inventoryservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.newbcoder_f5.ncf5inventoryservice.dto.InventoryResponse;
import dev.newbcoder_f5.ncf5inventoryservice.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;
    
    //# http://localhost:8082/api/inventory/iPhone 15 Pink
    // @GetMapping("/{sku-code}")
    // @ResponseStatus(HttpStatus.OK)
    // public boolean isInStock(@PathVariable("sku-code") String skuCode) {
    //     return inventoryService.isInStock(skuCode);
    // }

    //# http://localhost:8082/api/inventory?skuCode=iPhone 15 Pink&skuCode=Galaxy S23
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
        return inventoryService.isInStock(skuCode);
    }

}
