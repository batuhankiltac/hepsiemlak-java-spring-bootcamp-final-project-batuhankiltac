package com.batuhankiltac.emlakburadapayment.repository;

import com.batuhankiltac.emlakburadapayment.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}