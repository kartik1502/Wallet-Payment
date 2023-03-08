package org.training.walletpayment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.training.walletpayment.entity.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {



}
