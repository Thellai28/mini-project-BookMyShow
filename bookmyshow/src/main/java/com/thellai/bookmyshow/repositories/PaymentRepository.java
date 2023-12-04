package com.thellai.bookmyshow.repositories;

import com.thellai.bookmyshow.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
