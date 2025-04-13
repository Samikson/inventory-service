package com.demo.inventory_service.resource;

import com.demo.inventory_service.entity.Product;
import com.demo.inventory_service.service.ProductPublisher;
import com.demo.inventory_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.List;

//@RestController For Rest
//@RequestMapping("/products")
@Controller
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private ProductPublisher productPublisher;

    // @GetMapping For Rest
    @QueryMapping
    public List<Product> getProducts() {
        return service.getProducts();
    }

    //    @GetMapping("/{category}") For Rest
//    public List<Product> getProductsByCategory(@PathVariable String category) {
    @QueryMapping
    public List<Product> getProductsByCategory(@Argument String category) {
        return service.getProductsByCategory(category);
    }

    @MutationMapping
    public Product updateStock(@Argument int id,@Argument int stock) {
        return service.updateStock(id,stock);
    }

    @MutationMapping
    public Product receiveNewShipment(@Argument int id,@Argument int quantity) {
        return service.receiveNewShipment(id,quantity);
    }

    @MutationMapping
    public Product saveProduct(@Argument String name, @Argument String description, @Argument float price, @Argument int stock, @Argument String category) {
        Product newProduct = new Product();
        newProduct.setName(name);
        newProduct.setPrice(price);
        newProduct.setStock(stock);
        newProduct.setCategory(category);
        return service.saveProduct(newProduct);
    }



    @SubscriptionMapping
    public Flux<Product> productStockUpdates() {
        return productPublisher.getPublisher();
    }

}
