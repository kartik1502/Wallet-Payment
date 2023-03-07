package org.training.foodorderapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.training.foodorderapplication.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
