package org.training.walletpayment.service.implementation;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.training.walletpayment.entity.Wallet;
import org.training.walletpayment.repository.WalletRepository;
import org.training.walletpayment.service.WalletService;

@Service
public class WalletServiceImpl implements WalletService {
	
	@Autowired
	private WalletRepository repository;

	@Override
	public Optional<Wallet> findByWalletId(long walletId) {
		return repository.findById(walletId);
	}

	@Override
	public void save(Wallet wallet) {
		repository.save(wallet);
	}

}
