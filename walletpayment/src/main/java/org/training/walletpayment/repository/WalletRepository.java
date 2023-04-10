package org.training.walletpayment.repository;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.training.walletpayment.entity.User;
import org.training.walletpayment.entity.Wallet;

/**
 * 
 * The WalletRepository interface provides the methods to perform CRUD
 * operations on the Wallet entity.
 * 
 * It extends the JpaRepository interface provided by Spring Data JPA to inherit
 * the basic CRUD operations.
 */
public interface WalletRepository extends JpaRepository<Wallet, Long> {

	/**
	 * 
	 * Finds the wallet for the specified wallet ID and user.
	 * 
	 * @param walletId the wallet ID
	 * @param user     the user to whom the wallet belongs
	 * @return an Optional object containing the wallet if it exists, otherwise
	 *         empty
	 */
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Optional<Wallet> findByWalletIdAndUser(long walletId, User user);

}
