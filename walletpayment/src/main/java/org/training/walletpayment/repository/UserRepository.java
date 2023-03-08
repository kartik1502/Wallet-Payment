package org.training.walletpayment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.training.walletpayment.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUserId(int userId);

}
