package org.training.walletpayment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.training.walletpayment.entity.User;
import org.training.walletpayment.entity.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

	Optional<Wallet> findByWalletIdAndUser(long walletId, User user);



}
