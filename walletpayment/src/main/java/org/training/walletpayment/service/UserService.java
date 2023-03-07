package org.training.walletpayment.service;

import java.util.Optional;

import org.training.walletpayment.entity.User;

public interface UserService {

	Optional<User> findByUserId(int userId);
	
	
}
