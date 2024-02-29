package ru.lakeevda.paymentservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.lakeevda.paymentservice.entity.Account;
import ru.lakeevda.paymentservice.entity.Payment;
import ru.lakeevda.paymentservice.repository.AccountRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentServiceModuleTest {
    @Mock
    private AccountRepository accountRepository;
    @InjectMocks
    private PaymentService paymentService;
    private Account account1;
    private Account account2;
    private Payment payment;

    @BeforeEach
    void setUp() {
        account1 = new Account();
        account1.setId(1L);
        account1.setName("Счет1");
        account1.setBalance(new BigDecimal(10000));

        account2 = new Account();
        account2.setId(2L);
        account2.setName("Счет2");
        account2.setBalance(new BigDecimal(20000));

        payment = new Payment();
        payment.setCreditNumber(account1.getId());
        payment.setDebitNumber(account2.getId());
        payment.setSum(new BigDecimal(1000));
    }

    @Test
    void payment() {

        when(accountRepository.findById(account1.getId())).thenReturn(Optional.of(account1));
        when(accountRepository.findById(account2.getId())).thenReturn(Optional.of(account2));

        paymentService.payment(payment);

        assertThat(account1.getBalance()).isEqualTo(new BigDecimal(9000));
        assertThat(account2.getBalance()).isEqualTo(new BigDecimal(21000));
    }

    @Test
    void paymentRollback() {
        when(accountRepository.findById(account1.getId())).thenReturn(Optional.of(account1));
        when(accountRepository.findById(account2.getId())).thenReturn(Optional.of(account2));

        paymentService.paymentRollback(payment);

        assertThat(account1.getBalance()).isEqualTo(new BigDecimal(11000));
        assertThat(account2.getBalance()).isEqualTo(new BigDecimal(19000));
    }
}