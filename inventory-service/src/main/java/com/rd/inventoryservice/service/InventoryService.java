package com.rd.inventoryservice.service;

import com.rd.inventoryservice.repository.InventoryRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created at 24.03.2023
 *
 * @author Rıdvan DOĞAN
 */
@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRespository inventoryRespository;

    @Transactional(readOnly = true)
    public boolean isInStock(String skuCode) {
        return inventoryRespository.findBySkuCode(skuCode).isPresent();
    }
}
