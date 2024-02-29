package ru.lakeevda.shopclient.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.lakeevda.shopclient.entity.Payment;

@FeignClient(name = "payment-service")
public interface PaymentApi {

    @PostMapping()
    ResponseEntity<?> payment(@RequestBody Payment payment);

    @PostMapping("/rollback")
    void paymentRollback(@RequestBody Payment payment);

}
