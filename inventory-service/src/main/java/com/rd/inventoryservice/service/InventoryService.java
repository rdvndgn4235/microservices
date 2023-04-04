package com.rd.inventoryservice.service;

import com.rd.inventoryservice.dto.InventoryResponse;
import com.rd.inventoryservice.model.Inventory;
import com.rd.inventoryservice.repository.InventoryRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<InventoryResponse> isInStock(List<String> skuCodes) {
        return inventoryRespository.findAllBySkuCodeIn(skuCodes).stream()
                                   .map(inventory -> toInventoryResponse(inventory))
                                   .collect(Collectors.toList());
    }

    private InventoryResponse toInventoryResponse(Inventory inventory) {
        return InventoryResponse.builder()
                                .skuCode(inventory.getSkuCode())
                                .isInStock(inventory.getQuantity() > 0)
                                .build();
    }
}
