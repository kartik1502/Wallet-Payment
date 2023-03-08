package org.training.walletpayment.service;

import java.util.Optional;

import org.training.walletpayment.entity.User;
import org.training.walletpayment.entity.Wallet;

public interface UserService {

	Optional<User> findByUserId(int userId);

	Optional<User> findUserByUserIdAndWallets(int user, Wallet wallets);

}
