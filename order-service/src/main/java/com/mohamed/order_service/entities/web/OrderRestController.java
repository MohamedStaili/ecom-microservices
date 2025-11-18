package com.mohamed.order_service.entities.web;

import com.mohamed.order_service.entities.Order;
import com.mohamed.order_service.models.Customer;
import com.mohamed.order_service.models.Product;
import com.mohamed.order_service.repositories.OrderRepository;
import com.mohamed.order_service.repositories.ProductItemRepository;
import com.mohamed.order_service.services.CustomerRestClientService;
import com.mohamed.order_service.services.InventoryRestClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderRestController {
    private final OrderRepository orderRepository;
    private final ProductItemRepository productItemRepository;
    private final CustomerRestClientService customerRestClientService;
    private final InventoryRestClientService inventoryRestClientService;

    @GetMapping("/fullOrder/{id}")
    public Order getOrder(@PathVariable Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            Customer customer = customerRestClientService.customerById(order.getCustomerId());
            order.setCustomer(customer);
            order.getProductItems().forEach(productItem -> {
                Product product = inventoryRestClientService.productById(productItem.getProductId());
                productItem.setProduct(product);
            });
        }
        return order;

    }
}
