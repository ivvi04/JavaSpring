package ru.lakeevda.shopclient.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lakeevda.shopclient.entity.Product;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductApi {

    @GetMapping
    List<Product> getProducts();

    @PostMapping("/{id}/reserve")
    ResponseEntity<?> reserveProduct(@PathVariable Long id, @RequestParam int amount);

    @PostMapping("/{id}/reserve/rollback")
    void rollbackReserve(@PathVariable Long id, @RequestParam int amount);

    @PostMapping("/{id}")
    ResponseEntity<?> bay(@PathVariable Long id, @RequestParam int amount);

}
