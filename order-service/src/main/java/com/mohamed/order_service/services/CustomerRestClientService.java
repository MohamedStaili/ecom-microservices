package com.mohamed.order_service.services;

import com.mohamed.order_service.models.Customer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@FeignClient(name = "customer-service")
public interface CustomerRestClientService {

    @GetMapping(value = "/customers/{id}", params = "projection=fullCustomer")
    @CircuitBreaker(name = "customerServiceCB", fallbackMethod = "getDefaultCustomer")
    public Customer customerById(@PathVariable Long id);
    @CircuitBreaker(name = "customerServiceCB" , fallbackMethod = "getDefaultCustomers")
    @GetMapping(value = "/customers", params = "projection=fullCustomer")
    public PagedModel<Customer> allCustomers();

    default Customer getDefaultCustomer(Long id, Exception exception) {
        return  Customer.builder().id(id)
                .name("default Name")
                .email("default Email")
                .build();

    }
    default PagedModel<Customer> getDefaultCustomers(Exception exception) {
        return PagedModel.empty();
    }
}
