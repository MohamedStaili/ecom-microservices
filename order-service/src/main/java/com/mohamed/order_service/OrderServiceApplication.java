package com.mohamed.order_service;

import com.mohamed.order_service.entities.Order;
import com.mohamed.order_service.entities.ProductItem;
import com.mohamed.order_service.enums.OrderStatus;
import com.mohamed.order_service.models.Customer;
import com.mohamed.order_service.models.Product;
import com.mohamed.order_service.repositories.OrderRepository;
import com.mohamed.order_service.repositories.ProductItemRepository;
import com.mohamed.order_service.services.CustomerRestClientService;
import com.mohamed.order_service.services.InventoryRestClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}
@Bean
	CommandLineRunner runner(
			OrderRepository orderRepository ,
			ProductItemRepository productItemRepository,
			CustomerRestClientService customerRestClientService,
			InventoryRestClientService inventoryRestClientService
	){
		return args -> {
			List<Customer> customers = customerRestClientService.allCustomers().getContent().stream().toList();
			//customers.forEach(System.out::println);
			List<Product> products = inventoryRestClientService.allProducts().getContent().stream().toList();
			//products.forEach(System.out::println);
			Random random = new Random();
			for (int i = 0; i < 20; i++) {
				Order order = Order.builder()
						.customerId(customers.get(random.nextInt(customers.size())).getId())
						.status(Math.random()>0.5?OrderStatus.PENDING:OrderStatus.CREATED)
						.createAt(new Date())
						.build();
				Order savedOrder = orderRepository.save(order);
				for(int j = 0; j < products.size(); j++) {
					if(Math.random()>0.7){
						ProductItem productItem = ProductItem.builder()
								.productId(products.get(j).getId())
								.order(savedOrder)
								.price(products.get(j).getPrice())
								.quantity(1+random.nextInt(10))
								.discount(Math.random())
								.build();
						productItemRepository.save(productItem);
					}
					}

			}
		};
	}
}
