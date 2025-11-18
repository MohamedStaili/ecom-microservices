package com.mohamed.order_service.services;

import com.mohamed.order_service.models.Customer;
import com.mohamed.order_service.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "inventory-service")
public interface InventoryRestClientService {
    @GetMapping(value = "/products/{id}", params = "projection=fullProduct")
    Product productById(@PathVariable Long id);

    @GetMapping(value = "/products", params = "projection=fullProduct")
    PagedModel<Product> allProducts();
}
