package com.demo.inventory_service.service;

import com.demo.inventory_service.entity.Product;
import com.demo.inventory_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductPublisher productPublisher;

    public List<Product> getProducts() {
        return repository.findAll();
    }

    public List<Product> getProductsByCategory(String category) {
        return repository.findByCategory(category);
    }

    // Update Product
    public Product updateStock(int id,int quantity) {
        Product product = repository.findById(id).orElseThrow(() -> new RuntimeException("Product not found" + id));
        product.setStock(quantity);
        return repository.save(product);
    }

    // warehouse : receive new shipment
    public Product receiveNewShipment(int id,int quantity) {
        Product product = repository.findById(id).orElseThrow(() -> new RuntimeException("Product not found" + id));
        product.setStock(product.getStock() + quantity);
        Product updatedProduct = repository.save(product);
        productPublisher.publish(updatedProduct); // publish update
        return updatedProduct;
    }

    public Product saveProduct(Product newProduct) {
        return repository.save(newProduct);
    }
}
