package org.training.foodorderapplication.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.training.foodorderapplication.entity.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {

}
