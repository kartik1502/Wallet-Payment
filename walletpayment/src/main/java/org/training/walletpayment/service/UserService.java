package org.training.walletpayment.service;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.training.walletpayment.entity.User;

/**
 * This interface provides methods for retrieving a user by user ID.
 */
public interface UserService {

	/**
     * Retrieves a user by user ID.
     *
     * @param userId the user ID to search for
	 * @return an optional user object
     */
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Optional<User> findByUserId(int userId);

}
