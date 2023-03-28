package com.rd.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created at 24.03.2023
 *
 * @author Rıdvan DOĞAN
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItemsDto {
    private Integer id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
