package ru.lakeevda.shopclient.service;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import ru.lakeevda.shopclient.api.PaymentApi;
import ru.lakeevda.shopclient.api.ProductApi;
import ru.lakeevda.shopclient.entity.Product;
import ru.lakeevda.shopclient.entity.Payment;

import java.math.BigDecimal;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final PaymentApi paymentApi;

    private final ProductApi productApi;

    @Value("${shop.account.number}")
    private String shopAccount;

    @Retry(name = "serviceRetry")
    public List<Product> getProducts() {
        List<Product> productList = productApi.getProducts();
        productList.forEach(product -> product.setImageString(Base64.getEncoder().encodeToString(product.getImage())));
        return productList;
    }

    public void buyProduct(Long productId, int amount) {
        Product product = getProducts()
                .stream()
                .filter(prod -> prod.getId().equals(productId))
                .findFirst()
                .orElse(null);
        BigDecimal sum = product.getPrice().multiply(new BigDecimal(amount));

        ResponseEntity<?> response = productReserve(productId, amount);
        if (response.getStatusCode().is2xxSuccessful()) {
            response = payment(sum, 1L);
            if (response.getStatusCode().is2xxSuccessful()) {
                productBay(productId, amount);
            } else {
                paymentRollback(sum, 1L);
                reservedProductRollback(productId, amount);
            }
        } else {
            reservedProductRollback(productId, amount);
        }
    }

    @Retry(name = "serviceRetry")
    private ResponseEntity<?> productReserve(Long id, int amount)
            throws HttpClientErrorException {
        return productApi.reservedProduct(id, amount);
    }

    @Retry(name = "serviceRetry")
    private void reservedProductRollback(Long id, int amount)
            throws HttpClientErrorException {
        productApi.reservedProductRollback(id, amount);
    }

    @Retry(name = "serviceRetry")
    private void productBay(Long id, int amount)
            throws HttpClientErrorException {
        productApi.bayProduct(id, amount);
    }

    @Retry(name = "serviceRetry")
    private ResponseEntity<?> payment(BigDecimal sum, Long numberCredit)
            throws HttpClientErrorException {
        return paymentApi.payment(new Payment(numberCredit, Long.parseLong(shopAccount), sum));
    }

    @Retry(name = "serviceRetry")
    private void paymentRollback(BigDecimal sum, Long numberCredit)
            throws HttpClientErrorException {
        paymentApi.paymentRollback(new Payment(numberCredit, Long.parseLong(shopAccount), sum));
    }
}
