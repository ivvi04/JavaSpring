package ru.lakeevda.paymentservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lakeevda.paymentservice.entity.Account;
import ru.lakeevda.paymentservice.entity.Payment;
import ru.lakeevda.paymentservice.exception.ExcessAmountException;
import ru.lakeevda.paymentservice.exception.ResourceNotFoundException;
import ru.lakeevda.paymentservice.repository.AccountRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long id) throws RuntimeException {
        return accountRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Счет " + id + " не найден!"));
    }

    @Transactional
    public void payment(Payment payment) {
        Account creditAccount = getAccountById(payment.getCreditNumber());
        if((creditAccount.getBalance().compareTo(payment.getSum())) < 0) {
            throw new ExcessAmountException("Не достаточно средств!");
        }

        Account debitAccount = getAccountById(payment.getDebitNumber());

        creditAccount.setBalance(
                creditAccount.getBalance().subtract(payment.getSum()));
        debitAccount.setBalance(
                debitAccount.getBalance().add(payment.getSum()));

        accountRepository.save(creditAccount);
        accountRepository.save(debitAccount);
    }

    @Transactional
    public void paymentRollback(Payment payment) {
        Account debitAccount = getAccountById(payment.getDebitNumber());
        Account creditAccount = getAccountById(payment.getCreditNumber());

        debitAccount.setBalance(
                debitAccount.getBalance().subtract(payment.getSum()));
        creditAccount.setBalance(
                creditAccount.getBalance().add(payment.getSum()));

        accountRepository.save(creditAccount);
        accountRepository.save(debitAccount);
    }
}
