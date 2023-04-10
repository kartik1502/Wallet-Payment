package org.training.walletpayment.service.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.training.walletpayment.entity.User;
import org.training.walletpayment.entity.Wallet;
import org.training.walletpayment.repository.WalletRepository;
import org.training.walletpayment.service.WalletService;

/**
 * 
 * Implementation of {@link WalletService} interface
 */
@Service
public class WalletServiceImpl implements WalletService {

	@Autowired
	private WalletRepository repository;

	/*
	 * 
	 * @param walletId Id of the wallet
	 * 
	 * @param user User object to whom the wallet belongs to
	 * 
	 * @return Optional of Wallet object containing the wallet details
	 */
	@Override
	public Optional<Wallet> findByWalletId(long walletId, User user) {
		return repository.findByWalletIdAndUser(walletId, user);
	}

	/*
	 * Saves the wallet details to the database
	 * 
	 * @param wallet Wallet object to be saved
	 */
	@Override
	public void save(Wallet wallet) {
		repository.save(wallet);
	}

}
