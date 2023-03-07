package org.training.foodorderapplication.service.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.training.foodorderapplication.entity.User;
import org.training.foodorderapplication.repository.UserRepository;
import org.training.foodorderapplication.service.UserService;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;
	
	@Override
	public Optional<User> findByUserId(int userId) {
		return repository.findById(userId);
	}

}
