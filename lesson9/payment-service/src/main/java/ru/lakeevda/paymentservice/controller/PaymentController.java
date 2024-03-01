package ru.lakeevda.paymentservice.controller;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
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
    private final MeterRegistry meterRegistry;

    @GetMapping()
    public ResponseEntity<List<Account>> getAccounts(){
        meterRegistry.counter("payment_counter_general").increment();
        meterRegistry.counter("payment_counter_get_accounts").increment();
        return ResponseEntity.ok().body(paymentService.getAllAccounts());
    }

    @PostMapping()
    public ResponseEntity<Void> payment(@RequestBody Payment payment){
        meterRegistry.counter("payment_counter_general").increment();
        meterRegistry.counter("payment_counter_payment").increment();
        Timer.Sample sample = Timer.start(meterRegistry);
        paymentService.payment(payment);
        sample.stop(meterRegistry.timer("payment_timer_payment"));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/rollback")
    public ResponseEntity<Void> paymentRollback(@RequestBody Payment payment){
        meterRegistry.counter("payment_counter_general").increment();
        meterRegistry.counter("payment_counter_payment_rollback").increment();
        Timer.Sample sample = Timer.start(meterRegistry);
        paymentService.paymentRollback(payment);
        sample.stop(meterRegistry.timer("payment_timer_payment_rollback"));
        return ResponseEntity.ok().build();
    }
}
