package org.training.foodorderapplication.service;

import java.util.Optional;
import java.util.UUID;

import org.training.foodorderapplication.entity.Wallet;

public interface WalletService {

	Optional<Wallet> findByWalletId(UUID walletId);

}
