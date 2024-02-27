package ru.lakeevda.paymentservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lakeevda.paymentservice.entity.Account;
import ru.lakeevda.paymentservice.entity.Payment;
import ru.lakeevda.paymentservice.exception.ExcessAmountException;
import ru.lakeevda.paymentservice.repository.PaymentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    @Transactional
    public void payment(Payment payment) {
        Account creditAccount = paymentRepository.findByNumber(payment.getCreditNumber());
        if((creditAccount.getBalance().compareTo(payment.getSum())) < 0) {
            throw new ExcessAmountException("Не достаточно средств!");
        }

        Account debitAccount = paymentRepository.findByNumber(payment.getDebitNumber());

        creditAccount.setBalance(
                creditAccount.getBalance().subtract(payment.getSum()));
        debitAccount.setBalance(
                debitAccount.getBalance().add(payment.getSum()));

        paymentRepository.save(creditAccount);
        paymentRepository.save(debitAccount);
    }

    @Transactional
    public void rollbackPayment(Payment payment) {
        Account debitAccount = paymentRepository.findByNumber(payment.getDebitNumber());
        Account creditAccount = paymentRepository.findByNumber(payment.getCreditNumber());

        debitAccount.setBalance(
                debitAccount.getBalance().subtract(payment.getSum()));
        creditAccount.setBalance(
                creditAccount.getBalance().add(payment.getSum()));

        paymentRepository.save(creditAccount);
        paymentRepository.save(debitAccount);
    }

    public List<Account> getAllAccounts() {
        return paymentRepository.findAll();
    }
}
