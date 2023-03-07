package org.training.foodorderapplication.service;

import java.util.Optional;

import org.training.foodorderapplication.entity.User;

public interface UserService {

	Optional<User> findByUserId(int userId);

}
