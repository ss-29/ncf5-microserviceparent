package dev.newbcoder_f5.ncf5orderservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import dev.newbcoder_f5.ncf5orderservice.dto.InventoryResponse;
import dev.newbcoder_f5.ncf5orderservice.dto.OrderLineItemsDto;
import dev.newbcoder_f5.ncf5orderservice.dto.OrderRequest;
import dev.newbcoder_f5.ncf5orderservice.model.Order;
import dev.newbcoder_f5.ncf5orderservice.model.OrderLineItems;
import dev.newbcoder_f5.ncf5orderservice.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;
    
    public String placeOrder(OrderRequest orderRequest) {
        
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItems);

        //# get list of skuCodes
        // order.getOrderLineItemsList().stream().map(orderLineItem -> orderLineItem.getSkuCode());

        //# above line with method reference
        List<String> skuCodes = order.getOrderLineItemsList()
                        .stream()
                        .map(OrderLineItems::getSkuCode)
                        .toList();

        //@ call inventory service and place order if product is in stock
        InventoryResponse[] inventoryResponseResult = webClientBuilder.build().get()
            .uri("http://ncf5-inventoryservice/api/inventory",
                    uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
            .retrieve()
            .bodyToMono(InventoryResponse[].class)
            .block();

        //# even if one product is not available (in inventory), result will be false
        boolean allProductsInStock = Arrays.stream(inventoryResponseResult)
                        .allMatch(InventoryResponse::isInStock);

        if (allProductsInStock)
            orderRepository.save(order);
        else
            return "🛑 Product is not in stock 🛒, please try again later.";
        
        return "✅ Order placed successfully 🛒";

    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();

        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());

        return orderLineItems;
    }

}
