package com.rd.inventoryservice.repository;

import com.rd.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created at 24.03.2023
 *
 * @author Rıdvan DOĞAN
 */
@Repository
public interface InventoryRespository extends JpaRepository<Inventory, Integer> {
    List<Inventory> findAllBySkuCodeIn(List<String> skuCodes);
}
