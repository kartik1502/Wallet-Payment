package org.training.walletpayment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.training.walletpayment.entity.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
}
