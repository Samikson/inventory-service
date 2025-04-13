package com.demo.inventory_service.service;

import com.demo.inventory_service.entity.Product;
import lombok.Getter;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@Service
public class ProductPublisher {

    @Getter
    private final Flux<Product> publisher;
    private FluxSink<Product> productSink;

    public ProductPublisher() {
        this.publisher = Flux.<Product>create(emitter -> this.productSink = emitter, FluxSink.OverflowStrategy.LATEST).share();
    }

    public void publish(Product product) {
        if (productSink != null) {
            productSink.next(product);
        }
    }

}
