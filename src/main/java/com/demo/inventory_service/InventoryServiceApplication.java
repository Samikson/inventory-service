package com.demo.inventory_service;

import com.demo.inventory_service.entity.Product;
import com.demo.inventory_service.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class InventoryServiceApplication {

	@Autowired
	private ProductRepository repository;

	@PostConstruct
	public void initDB() {
		List<Product> products = Stream.of(
				new Product(50,74999.99f,"Electronics","Laptop"),
				new Product(100,39999.99f,"Electronics","Smartphone"),
				new Product(200,7999.99f,"Furniture","Office Chair"),
				new Product(50,1999.99f,"Desk Lamp","Furniture"),
				new Product(50,1999.99f,"Electronics","Monitor")
		).toList();
		//repository.saveAll(products);
	}

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

}
