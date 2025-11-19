package com.mohamed.order_service.entities;

import com.mohamed.order_service.enums.OrderStatus;
import com.mohamed.order_service.models.Customer;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Projection(name = "fullOrder" , types = Order.class)
public interface OrderProjection {
    Long getId();
    Date getCreateAt();
    OrderStatus getStatus();
    Long getCustomerId();

}
