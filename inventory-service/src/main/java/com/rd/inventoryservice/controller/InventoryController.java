package com.rd.inventoryservice.controller;

import com.rd.inventoryservice.dto.InventoryResponse;
import com.rd.inventoryservice.model.Inventory;
import com.rd.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created at 24.03.2023
 *
 * @author Rıdvan DOĞAN
 */

@RequestMapping("api/inventory")
@RestController
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse > inStock(@RequestParam List<String> skuCodes) {
        return inventoryService.isInStock(skuCodes);
    }
}
