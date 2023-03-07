package org.training.foodorderapplication.service.implementation;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.training.foodorderapplication.entity.Wallet;
import org.training.foodorderapplication.repository.WalletRepository;
import org.training.foodorderapplication.service.WalletService;

public class WalletServiceImpl implements WalletService {
	
	@Autowired
	private WalletRepository repository;

	@Override
	public Optional<Wallet> findByWalletId(UUID walletId) {
		return repository.findById(walletId);
	}

}
