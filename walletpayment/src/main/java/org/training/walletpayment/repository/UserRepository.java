package org.training.walletpayment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.training.walletpayment.entity.User;
import org.training.walletpayment.entity.Wallet;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByUserIdAndWallets(int user, Wallet wallets);

    Optional<User> findByUserId(int userId);
}
