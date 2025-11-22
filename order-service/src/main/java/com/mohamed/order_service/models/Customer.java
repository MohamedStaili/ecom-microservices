package com.mohamed.order_service.models;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class Customer {
    private Long id;
    private String name ;
    private String email;
}
