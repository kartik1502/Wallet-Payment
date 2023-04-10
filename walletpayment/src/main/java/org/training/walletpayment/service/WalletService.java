package org.training.walletpayment.service;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.training.walletpayment.entity.User;
import org.training.walletpayment.entity.Wallet;


public interface WalletService {

	/**
	 * Finds the wallet with the specified ID and user.
	 *
	 * @param walletId The ID of the wallet to find.
	 * @param user The user who owns the wallet.
	 * @return An {@code Optional} containing the wallet if found, or an empty {@code Optional} if not found.
	 */
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Optional<Wallet> findByWalletId(long walletId, User user);

	/**
	 * Saves the given wallet.
	 *
	 * @param wallet The wallet to save.
	 */
	void save(Wallet wallet);
}
