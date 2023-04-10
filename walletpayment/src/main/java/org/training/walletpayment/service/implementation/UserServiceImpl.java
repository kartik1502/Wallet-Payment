package org.training.walletpayment.service.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.training.walletpayment.entity.User;
import org.training.walletpayment.repository.UserRepository;
import org.training.walletpayment.service.UserService;

/**
 * 
 * Implementation class for UserService interface which provides methods to
 * interact with User data in the database.
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	/**
	 * 
	 * Returns an Optional containing the User entity with the given user ID, if
	 * present in the database.
	 * 
	 * @param userId an integer representing the ID of the User to be retrieved
	 * @return an Optional containing the User entity if it exists in the database,
	 *         otherwise empty
	 */

	@Override
	public Optional<User> findByUserId(int userId) {
		return repository.findById(userId);
	}

}
