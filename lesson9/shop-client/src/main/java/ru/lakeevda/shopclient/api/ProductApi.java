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

    @PostMapping("/{id}")
    ResponseEntity<?> bayProduct(@PathVariable Long id, @RequestParam int amount);

    @PostMapping("/{id}/reserved")
    ResponseEntity<?> reservedProduct(@PathVariable Long id, @RequestParam int amount);

    @PostMapping("/{id}/reserved/rollback")
    void reservedProductRollback(@PathVariable Long id, @RequestParam int amount);


}
