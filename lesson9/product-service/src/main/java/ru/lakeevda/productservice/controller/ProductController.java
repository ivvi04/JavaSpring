package ru.lakeevda.productservice.controller;

import lombok.RequiredArgsConstructor;
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

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(){
        return ResponseEntity.ok().body(productService.getAllProduct());
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(productService.getProductById(id));
    }

    @PostMapping("{id}")
    public ResponseEntity<Void> reduceAmount(@PathVariable("id") Long id,
                                             @RequestParam int amount){
        productService.reduceAmount(id, amount);
        return ResponseEntity.ok().build();
    }

    @PostMapping("{id}/reserve")
    public ResponseEntity<Void> reserveAmount(@PathVariable("id") Long id,
                                              @RequestParam int amount){
        productService.reservedProduct(id, amount);
        return ResponseEntity.ok().build();
    }

    @PostMapping("{id}/reserve/rollback")
    public ResponseEntity<Void> rollbackReserveAmount(@PathVariable("id") Long id,
                                                      @RequestParam int amount){
        productService.rollbackReservedProduct(id, amount);
        return ResponseEntity.ok().body(null);
    }

}
