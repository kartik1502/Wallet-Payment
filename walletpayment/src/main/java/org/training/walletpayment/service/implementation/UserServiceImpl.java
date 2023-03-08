package org.training.walletpayment.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.training.walletpayment.entity.User;
import org.training.walletpayment.entity.Wallet;
import org.training.walletpayment.repository.UserRepository;
import org.training.walletpayment.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;
	
	@Override
	public Optional<User> findByUserId(int userId) {
		return repository.findById(userId);
	}

	@Override
	public Optional<User> findUserByUserIdAndWallets(int user, Wallet wallets) {
		return repository.findUserByUserIdAndWallets(user, wallets);
	}

}
