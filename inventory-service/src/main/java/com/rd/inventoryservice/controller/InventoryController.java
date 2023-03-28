package com.rd.inventoryservice.controller;

import com.rd.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("skuCode")
    @ResponseStatus(HttpStatus.OK)
    public boolean inStock(@PathVariable("skuCode") String skuCode) {
        return inventoryService.isInStock(skuCode);
    }
}
