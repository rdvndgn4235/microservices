package com.rd.orderservice.service;

import com.rd.orderservice.dto.InventoryResponse;
import com.rd.orderservice.dto.OrderLineItemsDto;
import com.rd.orderservice.dto.OrderRequest;
import com.rd.orderservice.model.Order;
import com.rd.orderservice.model.OrderLineItems;
import com.rd.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created at 24.03.2023
 *
 * @author Rıdvan DOĞAN
 */
@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList().stream()
                                                          .map(this::mapToDto)
                                                          .collect(Collectors.toList());
        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes = order.getOrderLineItemsList().stream()
                                     .map(OrderLineItems::getSkuCode)
                                     .collect(Collectors.toList());

        // call inventory service and place order if product is in stock
        InventoryResponse[] inventoryResponses = webClientBuilder.build().get().uri("http://inventory-service/api/inventory",
                                                                  uriBuilder -> uriBuilder.queryParam("skuCodes", skuCodes).build())
                                                          .retrieve()
                                                          .bodyToMono(InventoryResponse[].class)
                                                          .block();

        boolean allMatch = Arrays.stream(inventoryResponses)
                                 .allMatch(InventoryResponse::isInStock);
        if (allMatch) {
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Product is not in the stock, please try again later.");
        }
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
