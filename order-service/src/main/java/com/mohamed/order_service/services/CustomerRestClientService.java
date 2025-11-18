package com.mohamed.order_service.services;

import com.mohamed.order_service.models.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "customer-service")
public interface CustomerRestClientService {
    @GetMapping(value = "/customers/{id}", params = "projection=fullCustomer")
    public Customer customerById(@PathVariable Long id);

    @GetMapping(value = "/customers", params = "projection=fullCustomer")
    public PagedModel<Customer> allCustomers();
}
