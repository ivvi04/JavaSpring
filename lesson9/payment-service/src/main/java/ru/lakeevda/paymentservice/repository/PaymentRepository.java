package ru.lakeevda.paymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lakeevda.paymentservice.entity.Account;

@Repository
public interface PaymentRepository extends JpaRepository<Account, Long> {

    Account findByNumber(Long creditNumber);
}
