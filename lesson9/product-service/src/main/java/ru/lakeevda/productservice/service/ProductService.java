package ru.lakeevda.productservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lakeevda.productservice.entity.Product;
import ru.lakeevda.productservice.exception.ExcessAmountException;
import ru.lakeevda.productservice.exception.QuantityLessThanZeroException;
import ru.lakeevda.productservice.exception.ResourceNotFoundException;
import ru.lakeevda.productservice.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public Product getProductById(Long id) throws RuntimeException {
        return productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Товар " + id + " не найден!"));
    }

    @Transactional
    public void reduceAmount(Long id, int amount) throws RuntimeException {
        if (amount <= 0) throw new QuantityLessThanZeroException("Количество должно быть больше 0!");
        Product product = getProductById(id);
        if (amount > product.getAmount())
            throw new ExcessAmountException("Заказ превышает остаток на складе!");
        product.setAmount(product.getAmount() - amount);
        product.setReserved(product.getReserved() - amount);
        productRepository.save(product);
    }

    @Transactional
    public void reservedProduct(Long id, int amount) throws RuntimeException {
        if (amount <= 0) throw new QuantityLessThanZeroException("Количество должно быть больше 0!");
        Product product = getProductById(id);
        if (amount > product.getAmount())
            throw new ExcessAmountException("Заказ превышает остаток на складе!");
        product.setReserved(amount);
        productRepository.save(product);
    }

    @Transactional
    public void rollbackReservedProduct(Long id, int amount){
        if (amount <= 0) throw new QuantityLessThanZeroException("Количество должно быть больше 0!");
        Product product = getProductById(id);
        product.setReserved(product.getReserved() - amount);
        productRepository.save(product);
    }
}
