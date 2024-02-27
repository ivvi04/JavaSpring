package ru.lakeevda.paymentservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lakeevda.paymentservice.entity.Account;
import ru.lakeevda.paymentservice.entity.Payment;
import ru.lakeevda.paymentservice.service.PaymentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping()
    public ResponseEntity<List<Account>> getAccounts(){
        return ResponseEntity.ok().body(paymentService.getAllAccounts());
    }

    @PostMapping()
    public ResponseEntity<Void> payment(@RequestBody Payment payment){
        paymentService.payment(payment);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/rollback")
    public ResponseEntity<Void> rollbackPayment(@RequestBody Payment payment){
        paymentService.rollbackPayment(payment);
        return ResponseEntity.ok().build();
    }
}
