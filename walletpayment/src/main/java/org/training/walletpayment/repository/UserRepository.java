package org.training.walletpayment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.training.walletpayment.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    
}
