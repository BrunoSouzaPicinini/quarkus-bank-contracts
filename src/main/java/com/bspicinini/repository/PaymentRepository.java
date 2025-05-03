package com.bspicinini.repository;

import java.util.List;

import com.bspicinini.repository.entity.Payment;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PaymentRepository implements PanacheRepository<Payment> {

	public List<Payment> findPaymentsByInstallmentId(Long installmentId) {
		return find("installment.id", installmentId).list();
	}
}