package ru.lakeevda.productservice.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lakeevda.productservice.entity.Product;
import ru.lakeevda.productservice.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class ProductController {
    private final ProductService productService;

    private final MeterRegistry meterRegistry;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(){
        meterRegistry.counter("product_counter_general").increment();
        meterRegistry.counter("product_counter_get_products").increment();
        return ResponseEntity.ok().body(productService.getAllProduct());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id){
        meterRegistry.counter("product_counter_general").increment();
        meterRegistry.counter("product_counter_get_product").increment();
        return ResponseEntity.ok().body(productService.getProductById(id));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> bayProduct(@PathVariable("id") Long id,
                                           @RequestParam int amount){
        meterRegistry.counter("product_counter_general").increment();
        meterRegistry.counter("product_counter_bay_product").increment();
        Timer.Sample sample = Timer.start(meterRegistry);
        productService.bayProduct(id, amount);
        sample.stop(meterRegistry.timer("product_timer_bay_product"));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/reserved")
    public ResponseEntity<Void> reservedProduct(@PathVariable("id") Long id,
                                                @RequestParam int amount){
        meterRegistry.counter("product_counter_general").increment();
        meterRegistry.counter("product_counter_reserved_product").increment();
        Timer.Sample sample = Timer.start(meterRegistry);
        productService.reservedProduct(id, amount);
        sample.stop(meterRegistry.timer("product_timer_reserved_product"));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/reserved/rollback")
    public ResponseEntity<Void> reservedProductRollback(@PathVariable("id") Long id,
                                                        @RequestParam int amount){
        meterRegistry.counter("product_counter_general").increment();
        meterRegistry.counter("product_counter_reserved_product_rollback").increment();
        Timer.Sample sample = Timer.start(meterRegistry);
        productService.reservedProductRollback(id, amount);
        sample.stop(meterRegistry.timer("product_timer_reserved_rollback"));
        return ResponseEntity.ok().body(null);
    }

}
