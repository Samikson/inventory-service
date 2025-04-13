package com.demo.inventory_service.repository;

import com.demo.inventory_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    List<Product> findByCategory(String category);
}
