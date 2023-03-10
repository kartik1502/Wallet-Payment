package org.training.walletpayment.service.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.training.walletpayment.entity.User;
import org.training.walletpayment.entity.Wallet;
import org.training.walletpayment.repository.WalletRepository;
import org.training.walletpayment.service.WalletService;

@Service
public class WalletServiceImpl implements WalletService {
	
	@Autowired
	private WalletRepository repository;

	@Override
	public Optional<Wallet> findByWalletId(long walletId,User user) {
		return repository.findByWalletIdAndUser(walletId,user);
	}

	@Override
	public void save(Wallet wallet) {
		repository.save(wallet);
	}

}
