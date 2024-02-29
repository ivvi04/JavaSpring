package ru.lakeevda.paymentservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.lakeevda.paymentservice.entity.Account;
import ru.lakeevda.paymentservice.entity.Payment;
import ru.lakeevda.paymentservice.repository.AccountRepository;
import ru.lakeevda.paymentservice.service.PaymentService;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class PaymentControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private PaymentController paymentController;
    @MockBean
    private AccountRepository accountRepository;
    private Account account1;
    private Account account2;
    private Payment payment;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(paymentController).build();

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
    void getProducts() throws Exception {
        when(accountRepository.findById(account1.getId())).thenReturn(Optional.of(account1));
        when(accountRepository.findById(account2.getId())).thenReturn(Optional.of(account2));

        mockMvc.perform(get("/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void payment() throws Exception {
        when(accountRepository.findById(account1.getId())).thenReturn(Optional.of(account1));
        when(accountRepository.findById(account2.getId())).thenReturn(Optional.of(account2));

        mockMvc.perform(post("/").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(payment)))
                .andExpect(status().isOk());

        assertEquals(account1.getBalance(), new BigDecimal(9000));
        assertEquals(account2.getBalance(), new BigDecimal(21000));
    }

    @Test
    void paymentRollback() throws Exception {
        when(accountRepository.findById(account1.getId())).thenReturn(Optional.of(account1));
        when(accountRepository.findById(account2.getId())).thenReturn(Optional.of(account2));

        mockMvc.perform(post("/rollback").contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(payment)))
                .andExpect(status().isOk());

        assertEquals(account1.getBalance(), new BigDecimal(11000));
        assertEquals(account2.getBalance(), new BigDecimal(19000));
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}