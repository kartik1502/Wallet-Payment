package org.training.walletpayment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.training.walletpayment.entity.User;
import org.training.walletpayment.entity.Wallet;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByUserIdAndWallets(int user, Wallet wallets);
}
